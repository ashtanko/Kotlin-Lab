import com.diffplug.gradle.spotless.SpotlessPlugin
import io.gitlab.arturbosch.detekt.Detekt
import java.util.Locale
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectJvmTarget = "11"
val satisfyingNumberOfCores = Runtime.getRuntime().availableProcessors().div(2).takeIf { it > 0 } ?: 1
val ktlint: Configuration by configurations.creating
val isK2Enabled = false
val k2CompilerArg = if(isK2Enabled) listOf("-Xuse-k2") else emptyList()

group = "dev.shtanko"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.7.0"
    java
    jacoco
    idea
    // detekt linter - read more: https://detekt.github.io/detekt/gradle.html
    id("io.gitlab.arturbosch.detekt") version Versions.DETEKT
    id("org.jetbrains.dokka") version "1.6.21"
    id("com.diffplug.spotless") version "6.3.0"
    id("com.autonomousapps.dependency-analysis") version "1.0.0-rc01"
    id("info.solidsoft.pitest") version "1.7.4"
    kotlin("plugin.serialization") version "1.6.21"
    kotlin("kapt") version "1.7.0"
    application
}

apply<kotlinx.atomicfu.plugin.gradle.AtomicFUGradlePlugin>()

application {
    mainClass.set("dev.shtanko.concurrency.jvm.DeadLockSampleKt")
    mainClass.set("dev.shtanko.di.dagger.ModulesKt")
}

jacoco {
    toolVersion = "0.8.8"
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies.classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:${Versions.ATOMICFU}")
}

dependencies {
    implementation(kotlin("reflect", "1.7.0"))
    implementation(kotlin("stdlib-jdk8", "1.7.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:${Versions.COROUTINES}")

    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("io.reactivex.rxjava3:rxjava:${Versions.RX_JAVA}")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation("org.jetbrains.kotlinx:lincheck:${Versions.LINCHECK}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    ktlint("com.pinterest:ktlint:0.45.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:retrofit-mock:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    implementation("com.google.dagger:dagger:${Versions.DAGGER}")
    kapt("com.google.dagger:dagger-compiler:${Versions.DAGGER}")
    kapt("com.google.auto.value:auto-value:1.9")
    implementation("com.google.auto.value:auto-value-annotations:1.9")
    implementation("org.openjdk.jmh:jmh-core:1.35")

    kapt("org.openjdk.jmh:jmh-generator-annprocess:1.35")
    testImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.34")
    testImplementation("org.openjdk.jmh:jmh-core-benchmarks:1.35")
    testImplementation("org.jetbrains.kotlinx:lincheck:${Versions.LINCHECK}")
    testApi("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.kotlintest:kotlintest-core:3.4.2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("ch.qos.logback:logback-core:1.2.11")
    testImplementation("ch.qos.logback:logback-classic:1.2.11")
    testImplementation("io.reactivex.rxjava3:rxjava:${Versions.RX_JAVA}")
    testImplementation("com.carrotsearch:junit-benchmarks:0.7.2")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("io.mockk:mockk:1.12.4")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.DETEKT}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

repositories {
    google()
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

jacoco {

}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
}

plugins.withId("info.solidsoft.pitest") {
    configure<info.solidsoft.gradle.pitest.PitestPluginExtension> {
        jvmArgs.set(listOf("-Xmx1024m"))
        avoidCallsTo.set(setOf("kotlin.jvm.internal", "kotlin.Result"))
        targetClasses.set(setOf("dev.shtanko.algorithms.*"))
        targetTests.set(setOf("dev.shtanko.algorithms.*"))
        pitestVersion.set("1.4.11")
        verbose.set(true)
        threads.set(System.getenv("PITEST_THREADS")?.toInt() ?: satisfyingNumberOfCores)
        outputFormats.set(setOf("XML", "HTML"))
        testPlugin.set("junit5")
        junit5PluginVersion.set("0.12")
    }
}

spotless {
    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/spotless/*.kt")
                )
            )
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
    //apply<KtlintPlugin>()
    apply<SpotlessPlugin>()
}

fun isLinux(): Boolean {
    val osName = System.getProperty("os.name").toLowerCase(Locale.ROOT)
    return listOf("linux", "mac os", "macos").contains(osName)
}

tasks {

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
        reports {
            html.required.set(true)
            xml.required.set(true)
            xml.outputLocation.set(file("$buildDir/reports/jacoco/report.xml"))
        }
        executionData(file("build/jacoco/test.exec"))
    }

    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = projectJvmTarget
            freeCompilerArgs = freeCompilerArgs + "-Xuse-experimental=kotlin.Experimental" + k2CompilerArg
        }
    }

    withType<Test>().configureEach {
        jvmArgs = listOf(
            "-Dkotlintest.tags.exclude=Integration,EndToEnd,Performance",
        )
        useJUnitPlatform {
            includeEngines("spek2", "junit-jupiter")
        }
        testLogging {
            events("passed", "skipped", "failed")
        }
        testLogging.showStandardStreams = true
        useJUnitPlatform()
    }

    withType<Detekt>().configureEach {
        jvmTarget = projectJvmTarget
    }

    withType<Detekt> {
        description = "Runs over whole code base without the starting overhead for each module."
        parallel = true
        baseline.set(file("$rootDir/config/detekt/detekt-baseline.xml"))
        config.from(file("config/detekt/detekt.yml"))

        include("**/*.kt")
        include("**/*.kts")
        exclude(".*/resources/.*")
        exclude(".*/build/.*")
        exclude("/versions.gradle.kts")
        exclude("buildSrc/settings.gradle.kts")

        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("build/reports/detekt/detekt.xml"))
            html.required.set(true)
            txt.required.set(true)
        }
    }

    withType<Test> {
        maxParallelForks = satisfyingNumberOfCores
    }

    // config JVM target to 11 for kotlin compilation tasks
    withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = projectJvmTarget
    }
}
