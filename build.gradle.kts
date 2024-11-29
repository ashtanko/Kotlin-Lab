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
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0

val projectJvmTarget = 17
val satisfyingNumberOfCores = Runtime.getRuntime().availableProcessors().div(2).takeIf { it > 0 } ?: 1
val ktLintConfig: Configuration by configurations.creating
val isK2Enabled = true
val k2CompilerArg = if (isK2Enabled) listOf("-Xuse-k2") else emptyList()
val outputDir = "${project.layout.buildDirectory}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
val kotlinVersion = KOTLIN_2_0

fun isLinux(): Boolean {
    val osName = System.getProperty("os.name").lowercase()
    return listOf("linux", "mac os", "macos").contains(osName)
}

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    application
    jacoco
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.16"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    // id ("org.sonarqube") version "4.4.1.3373"
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
    alias(libs.plugins.protobuf)
}

jacoco {
    toolVersion = "0.8.11"
}

//sonarqube {
//    properties {
//        setProperty("sonar.projectKey", "ashtanko_kotlab")
//        setProperty("sonar.organization", "ashtanko")
//        setProperty("sonar.host.url", "https://sonarcloud.io")
//    }
//}

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

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktLintConfig
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktLintConfig
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
}

plugins.withId("info.solidsoft.pitest") {
    configure<info.solidsoft.gradle.pitest.PitestPluginExtension> {
        jvmArgs.set(listOf("-Xmx2048m"))
        avoidCallsTo.set(setOf("kotlin.jvm.internal", "kotlin.Result"))
        targetClasses.set(setOf("dev.shtanko.*"))
        targetTests.set(setOf("dev.shtanko.*"))
        pitestVersion.set("1.15.0")
        verbose.set(true)
        timestampedReports.set(false)
        threads.set(System.getenv("PITEST_THREADS")?.toInt() ?: satisfyingNumberOfCores)
        outputFormats.set(setOf("XML", "HTML"))
        testPlugin.set("junit5")
        junit5PluginVersion.set("1.0.0")
    }
}

spotless {
    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/spotless/*.kt"),
                ),
            ),
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        val delimiter = "^(package|object|import|interface|internal|@file|//startfile)"
        val licenseHeaderFile = rootProject.file("spotless/copyright.kt")
        licenseHeaderFile(licenseHeaderFile, delimiter)
    }
}

subprojects {
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
}

koverReport {
    verify {
        rule {
            minBound(80)
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
            "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED",
            "--add-exports", "java.base/jdk.internal.util=ALL-UNNAMED",
            "--add-exports", "java.base/sun.security.action=ALL-UNNAMED",
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

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "$projectJvmTarget"
            freeCompilerArgs = freeCompilerArgs + k2CompilerArg
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

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "$projectJvmTarget"
        }
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
        implementation("org.openjdk.jol:jol-core:0.17")

        ktLintConfig(ktlint)
        implementation(jsoup)
        implementation("com.google.protobuf:protobuf-java:3.19.1")
        implementation("com.google.protobuf:protobuf-kotlin-lite:3.19.6")
        implementation("io.grpc:grpc-stub:1.15.1")
        implementation("io.grpc:grpc-protobuf:1.15.1")


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
            "${PROPERTIES_KEY.first}" to report.metrics.numberOfProperties,
            "${FUNCTIONS_KEY.first}" to report.metrics.numberOfFunctions,
            "${CLASSES_KEY.first}" to report.metrics.numberOfClasses,
            "${PACKAGES_KEY.first}" to report.metrics.numberOfPackages,
            "${KT_FILES_KEY.first}" to report.metrics.numberOfKtFiles,
        )

        val metricsListColumns = metricsList.map { it.first }
        val metricsListRows = listOf(metricsList.map { "${it.second}" })

        val complexityList = listOf(
            "${LOC_KEY.second}" to report.complexityReport.linesOfCode,
            "${SLOC_KEY.second}" to report.complexityReport.sourceLinesOfCode,
            "${LLOC_KEY.second}" to report.complexityReport.logicalLinesOfCode,
            "${CLOC_KEY.second}" to report.complexityReport.commentLinesOfCode,
            "${MCC_KEY.second}" to report.complexityReport.cyclomaticComplexity,
            "${CC_KEY.second}" to report.complexityReport.cognitiveComplexity,
            "${CS_KEY.second}" to report.complexityReport.codeSmells,
            "${CSR_KEY.second}" to report.complexityReport.commentSourceRatio,
            "${MCC_PER_LLOC_KEY.second}" to report.complexityReport.mccPer1000Lloc,
            "${CS_KEY.second}" to report.complexityReport.codeSmellsPer1000Lloc,
        )

        val complexityListColumns = complexityList.map { it.first }
        val complexityListRows = listOf(complexityList.map { "${it.second}" })
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
        val separator = columnWidths.map {
            "-".repeat(it).padEnd(it)
        }
            .joinToString("|", prefix = "|", postfix = "|")

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
