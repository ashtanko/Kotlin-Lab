/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

val projectJvmTarget = 17
val satisfyingNumberOfCores = Runtime.getRuntime().availableProcessors().div(2).takeIf { it > 0 } ?: 1
val kotlinVersion = KOTLIN_2_2

fun isLinux(): Boolean {
    val osName = System.getProperty("os.name").lowercase()
    return listOf("linux", "mac os", "macos").contains(osName)
}

private val compilerArgs = listOf(
    "-opt-in=kotlin.RequiresOptIn",
    "-Xcontext-parameters",
    "-Xcontext-sensitive-resolution",
    "-Xannotation-target-all",
    "-Xannotation-default-target=param-property",
    "-Xnested-type-aliases",
    "-Xannotations-in-metadata",
    "-Xnon-local-break-continue",
)

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    application
    jacoco
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.18.1"
    idea
    alias(libs.plugins.kt.jvm)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dependency.analysis)
    alias(libs.plugins.pitest)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.diktat)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.protobuf)
}

jacoco {
    toolVersion = "0.8.13"
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.kotlin.link") }
    gradlePluginPortal()
    maven("https://plugins.gradle.org/m2/")
}

application {
    mainClass.set("link.kotlin.scripts.Application")
    mainClass.set("dev.shtanko.report.ReportParserKt")
}

pitest {
    jvmArgs.set(listOf("-Xmx8192m"))
    avoidCallsTo.set(setOf("kotlin.jvm.internal", "kotlin.Result"))
    targetClasses.set(setOf("dev.shtanko.*"))
    targetTests.set(setOf("dev.shtanko.*"))
    pitestVersion.set("1.15.0")
    verbose.set(true)
    timestampedReports.set(false)
    threads.set(System.getenv("PITEST_THREADS")?.toInt() ?: satisfyingNumberOfCores)
    outputFormats.set(setOf("XML", "HTML"))
    testPlugin.set("junit5")
    junit5PluginVersion = "1.2.1"
    setWithHistory(true)
    mutationThreshold.set(40)
}

subprojects {
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("${layout.buildDirectory}/**/*.kt")
            ktlint().editorConfigOverride(
                mapOf(
                    "indent_size" to "2",
                    "continuation_indent_size" to "2",
                ),
            )
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("${layout.buildDirectory}/**/*.kts")
            licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("${layout.buildDirectory}/**/*.xml")
            licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(^(?![\\/ ]\\*).*$)")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}

kover {
    reports {
        verify {
            rule {
                minBound(80)
            }
        }
    }
}

tasks {
    named("distZip") {
        dependsOn(withType<ShadowJar>())
    }

    withType<ShadowJar> {
        print("Build Report Parser: $name")
        archiveFileName.set("detekt_report_parser.jar")
        archiveVersion.set("")
        archiveClassifier.set("")
        manifest {
            attributes(
                "Main-Class" to "dev.shtanko.report.ReportParserKt",
                "Implementation-Version" to project.version,
            )
        }
    }

    withType<Test> {
        maxParallelForks = 1
        jvmArgs(
            "--add-opens",
            "java.base/jdk.internal.misc=ALL-UNNAMED",
            "--add-exports",
            "java.base/jdk.internal.util=ALL-UNNAMED",
            "--add-exports",
            "java.base/sun.security.action=ALL-UNNAMED",
        )
    }

    register<GenerateDetektReportTask>("detektReportToMdTask") {
        sourceDirectory = file("${System.getProperty("user.dir")}/build/reports/detekt")
        metricsReportFile = file("${System.getProperty("user.dir")}/build/reports/detekt/metrics.md")
        complexityReportFile = file("${System.getProperty("user.dir")}/build/reports/detekt/complexity.md")
    }

    compileKotlin {
        compilerOptions {
            apiVersion.set(kotlinVersion)
            languageVersion.set(kotlinVersion)
        }
    }
    kotlin {
        jvmToolchain(projectJvmTarget)
    }
    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    minimum = "0.5".toBigDecimal()
                }
            }
        }
    }

    register<Copy>("copyGitHooks") {
        description = "Copies the git hooks from scripts/git-hooks to the .git folder."
        group = "git hooks"
        from("$rootDir/scripts/git-hooks/") {
            include("**/*.sh")
            rename("(.*).sh", "$1")
        }
        into("$rootDir/.git/hooks")
    }

    register<Exec>("installGitHooks") {
        description = "Installs the pre-commit git hooks from scripts/git-hooks."
        group = "git hooks"
        workingDir(rootDir)
        commandLine("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn(named("copyGitHooks"))
        onlyIf {
            isLinux()
        }
        doLast {
            logger.info("Git hooks installed successfully.")
        }
    }

    register<Delete>("deleteGitHooks") {
        description = "Delete the pre-commit git hooks."
        group = "git hooks"
        delete(fileTree(".git/hooks/"))
    }

    afterEvaluate {
        tasks["clean"].dependsOn(tasks.named("installGitHooks"))
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            listOf(
                html, xml, csv,
            ).map { it.required }.forEach { it.set(true) }
        }
    }

    withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            compilerArgs.forEach(freeCompilerArgs::add)
        }
    }

    withType<io.gitlab.arturbosch.detekt.Detekt> {
        description = "Runs over whole code base without the starting overhead for each module."
        parallel = true
        baseline.set(file("$rootDir/config/detekt/detekt-baseline.xml"))
        config.from(file("$rootDir/config/detekt/detekt.yml"))
        jvmTarget = "$projectJvmTarget"

        setSource(files("src/main/kotlin", "src/test/kotlin"))
        setOf(
            "**/*.kt",
            "**/*.kts",
            ".*/resources/.*",
            ".*/build/.*",
            "/versions.gradle.kts",
        ).forEach {
            include(it)
        }

        reports {
            reports.apply {
                listOf(xml, html, txt, md).map { it.required }.forEach {
                    it.set(true)
                }
            }
        }
    }

    withType<DetektCreateBaselineTask> {
        jvmTarget = "$projectJvmTarget"
    }

    withType<Test>().configureEach {
        jvmArgs = listOf(
            "-Dkotlintest.tags.exclude=Integration,EndToEnd,Performance",
        )
        testLogging {
            events("passed", "skipped", "failed")
        }
        testLogging.showStandardStreams = true
        useJUnitPlatform()
        finalizedBy(withType(JacocoReport::class.java))
    }
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.apply {
            listOf(
                "-Xexpect-actual-classes",
                "-Xwhen-guards",
                "-Xnon-local-break-continue",
                "-Xmulti-dollar-interpolation",
            ).forEach(::add)
        }
        apiVersion.set(KOTLIN_2_2)
        languageVersion.set(KOTLIN_2_2)
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val kotlin by registering {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") { because("required for pitest") }
    libs.apply {
        kotlin.apply {
            implementation(stdlib)
            implementation(reflect)
            implementation(coroutines)
            implementation(coroutines.slf4j)
            implementation(coroutines.debug)
        }
        implementation(slf4j)
        implementation(rxjava)
        implementation(rxkotlin)
        implementation(lincheck)
        implementation(kotlin.serialization.json)
        implementation(retrofit)
        implementation(retrofit.mock)
        implementation(okhttp)
        implementation(sandwich.retrofit)
        implementation(okhttp.logging)
        implementation("org.openjdk.jol:jol-core:0.17")
        implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
        implementation(jsoup)
        implementation("com.google.protobuf:protobuf-java:4.31.1")
        implementation("com.google.protobuf:protobuf-kotlin-lite:4.31.1")
        implementation("io.grpc:grpc-stub:1.73.0")
        implementation("io.grpc:grpc-protobuf:1.73.0")


        testImplementation(mockk)
        testImplementation(junit)
        testImplementation(lincheck)
        testApi(kotlin.coroutines.core)
        testImplementation(kotlin.coroutines.test)
        testImplementation(kotlintest.core)
        testImplementation(kotlintest.junit5)
        testImplementation(assertj)
        testImplementation(hamcrest)
        testImplementation(mockk)
        testImplementation(mockito)
        testImplementation(mockito.kotlin)
        testImplementation(logback)
        testImplementation(logback.classic)
        testImplementation(rxjava)
        testImplementation(kotlin.serialization.json)
        testImplementation(kotest)
        testImplementation(kotest.assertions)
        testImplementation(kotest.property)
        testImplementation(okhttp.mockwebserver)
        testImplementation(turbine)
        testImplementation(truth)
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

abstract class GenerateDetektReportTask : DefaultTask() {

    @get:InputDirectory
    lateinit var sourceDirectory: File

    @get:OutputFile
    lateinit var metricsReportFile: File

    @get:OutputFile
    lateinit var complexityReportFile: File

    companion object {
        private val PROPERTIES_KEY = "number of properties" to "Props"
        private val FUNCTIONS_KEY = "number of functions" to "Funcs"
        private val CLASSES_KEY = "number of classes" to "Classes"
        private val PACKAGES_KEY = "number of packages" to "Pkgs"
        private val KT_FILES_KEY = "number of kt files" to "Kt Files"

        private val LOC_KEY = "loc" to "lines of code (loc)"
        private val SLOC_KEY = "sloc" to "source lines of code (sloc)"
        private val LLOC_KEY = "lloc" to "logical lines of code (lloc)"
        private val CLOC_KEY = "cloc" to "comment lines of code (cloc)"
        private val MCC_KEY = "mcc" to "cyclomatic complexity (mcc)"
        private val CC_KEY = "cognitive complexity" to "cognitive complexity"
        private val CSR_KEY = "comment source ratio" to "comment source ratio"
        private val MCC_PER_LLOC_KEY = "mcc per lloc" to "mcc per 1,000 lloc"
        private val CS_KEY = "code smells" to "number of total code smells"

    }

    @TaskAction
    fun generateReport() {
        val report = parseDetektFile("${sourceDirectory.path}/detekt.md")
        val metricsList = listOf(
            PROPERTIES_KEY.first to report.metrics.numberOfProperties,
            FUNCTIONS_KEY.first to report.metrics.numberOfFunctions,
            CLASSES_KEY.first to report.metrics.numberOfClasses,
            PACKAGES_KEY.first to report.metrics.numberOfPackages,
            KT_FILES_KEY.first to report.metrics.numberOfKtFiles,
        )

        // val metricsListColumns = metricsList.map { it.first }
        // val metricsListRows = listOf(metricsList.map { "${it.second}" })

        val complexityList = listOf(
            LOC_KEY.second to report.complexityReport.linesOfCode,
            SLOC_KEY.second to report.complexityReport.sourceLinesOfCode,
            LLOC_KEY.second to report.complexityReport.logicalLinesOfCode,
            CLOC_KEY.second to report.complexityReport.commentLinesOfCode,
            MCC_KEY.second to report.complexityReport.cyclomaticComplexity,
            CC_KEY.second to report.complexityReport.cognitiveComplexity,
            CS_KEY.second to report.complexityReport.codeSmells,
            CSR_KEY.second to report.complexityReport.commentSourceRatio,
            MCC_PER_LLOC_KEY.second to report.complexityReport.mccPer1000Lloc,
            CS_KEY.second to report.complexityReport.codeSmellsPer1000Lloc,
        )

        // val complexityListColumns = complexityList.map { it.first }
        // val complexityListRows = listOf(complexityList.map { "${it.second}" })
        writeReport(metricsList.map { "${it.second} ${it.first}" }.toSet(), "Metrics", false, metricsReportFile)
        writeReport(
            complexityList.map { "${it.second} ${it.first}" }.toSet(),
            "Complexity Report",
            true,
            complexityReportFile,
        )
        println("Report generated at: ${metricsReportFile.absolutePath}")
    }

    private fun writeReport(report: Set<String>, header: String, isLast: Boolean, file: File) {
        val metricsSb = StringBuilder()
        metricsSb.append("\n")
        metricsSb.append("### $header")
        metricsSb.append("\n")
        metricsSb.append("```text")
        report.forEach {
            metricsSb.append("\n")
            metricsSb.append(it)
        }
        metricsSb.append("\n")
        metricsSb.append("```")
        if (!isLast) {
            metricsSb.append("\n")
            metricsSb.append("\n")
        }
        file.writeText(metricsSb.toString())
    }

    private fun generateMarkdownTable(
        columns: List<String>,
        rows: List<List<String>>,
        newline: String = "\n",
    ): Triple<String, String, String> {
        if (columns.isEmpty()) return Triple("", "", "")

        // Calculate the maximum width for each column
        val columnWidths = columns.map { it.length }.toMutableList()
        rows.forEach { row ->
            row.forEachIndexed { index, cell ->
                if (index < columnWidths.size) {
                    columnWidths[index] = maxOf(columnWidths[index], cell.length)
                }
            }
        }

        // Helper function to pad cells to match the column width
        fun padCell(cell: String, width: Int): String = cell.padEnd(width)

        // Generate the header row
        val header = columns.mapIndexed { index, col ->
            padCell(col, columnWidths[index])
        }.joinToString("|", prefix = "|", postfix = "|")

        // Generate the separator row
        val separator = columnWidths.joinToString("|", prefix = "|", postfix = "|") {
            "-".repeat(it).padEnd(it)
        }

        // Generate the data rows
        val dataRows = rows.joinToString(newline) { row ->
            row.mapIndexed { index, cell ->
                padCell(cell, columnWidths[index])
            }.joinToString("|", prefix = "|", postfix = "|")
        }

        // Combine header, separator, and rows into the final Markdown table
        return Triple(header, separator, dataRows)
    }

    data class Metrics(
        val numberOfProperties: Int,
        val numberOfFunctions: Int,
        val numberOfClasses: Int,
        val numberOfPackages: Int,
        val numberOfKtFiles: Int,
    )

    data class ComplexityReport(
        val linesOfCode: Int,
        val sourceLinesOfCode: Int,
        val logicalLinesOfCode: Int,
        val commentLinesOfCode: Int,
        val cyclomaticComplexity: Int,
        val cognitiveComplexity: Int,
        val codeSmells: Int,
        val commentSourceRatio: Int, // Changed to Int to fit the example, may need adjustment
        val mccPer1000Lloc: Int,
        val codeSmellsPer1000Lloc: Int,
    )

    data class DetektReport(
        val metrics: Metrics,
        val complexityReport: ComplexityReport,
        val findings: Int,
        val version: String,
        val timestamp: String,
    )

    private fun parseDetektFile(filePath: String): DetektReport {
        val file = File(filePath)
        val lines = file.readLines()

        val metrics = Metrics(
            numberOfProperties = extractMetricValue(lines, "number of properties"),
            numberOfFunctions = extractMetricValue(lines, "number of functions"),
            numberOfClasses = extractMetricValue(lines, "number of classes"),
            numberOfPackages = extractMetricValue(lines, "number of packages"),
            numberOfKtFiles = extractMetricValue(lines, "number of kt files"),
        )

        val complexityReport = ComplexityReport(
            linesOfCode = extractComplexityValue(lines, "lines of code (loc)"),
            sourceLinesOfCode = extractComplexityValue(lines, "source lines of code (sloc)"),
            logicalLinesOfCode = extractComplexityValue(lines, "logical lines of code (lloc)"),
            commentLinesOfCode = extractComplexityValue(lines, "comment lines of code (cloc)"),
            cyclomaticComplexity = extractComplexityValue(lines, "cyclomatic complexity (mcc)"),
            cognitiveComplexity = extractComplexityValue(lines, "cognitive complexity"),
            codeSmells = extractComplexityValue(lines, "number of total code smells"),
            commentSourceRatio = extractPercentageValue(lines, "comment source ratio"),
            mccPer1000Lloc = extractComplexityValue(lines, "mcc per 1,000 lloc"),
            codeSmellsPer1000Lloc = extractComplexityValue(lines, "code smells per 1,000 lloc"),
        )

        val findings = extractFindings(lines)
        val version = extractVersion(lines)
        val timestamp = extractTimestamp(lines)

        return DetektReport(metrics, complexityReport, findings, version, timestamp)
    }

    private fun extractMetricValue(lines: List<String>, metric: String): Int {
        val prefix = "* "
        val suffix = " $metric"
        return lines.find { it.startsWith(prefix) && it.endsWith(suffix) }
            ?.substringAfter(prefix)
            ?.substringBefore(suffix)
            ?.replace(",", "")
            ?.toIntOrNull() ?: 0
    }

    private fun extractComplexityValue(lines: List<String>, metric: String): Int {
        val prefix = "* "
        return lines.find { it.startsWith(prefix) && it.contains(metric) }
            ?.substringAfter(prefix)
            ?.substringBefore(metric)
            ?.replace(",", "")
            ?.trim()
            ?.toIntOrNull() ?: 0
    }

    private fun extractPercentageValue(lines: List<String>, metric: String): Int {
        val prefix = "* "
        val suffix = " $metric"
        return lines.find { it.startsWith(prefix) && it.endsWith(suffix) }
            ?.substringAfter(prefix)
            ?.substringBefore(suffix)
            ?.replace(",", "")
            ?.replace("%", "")
            ?.trim()
            ?.toIntOrNull() ?: 0
    }

    private fun extractFindings(lines: List<String>): Int {
        return lines.find { it.startsWith("* Findings") }
            ?.substringAfter("* Findings (")
            ?.substringBefore(")")
            ?.toIntOrNull() ?: 0
    }

    private fun extractVersion(lines: List<String>): String {
        return lines.find { it.contains("detekt version") }
            ?.substringAfter("detekt version ")
            ?.substringBefore(" ") ?: ""
    }

    private fun extractTimestamp(lines: List<String>): String {
        return lines.find { it.contains("on") }
            ?.substringAfter("on ") ?: ""
    }
}
