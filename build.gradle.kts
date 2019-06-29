import org.gradle.api.tasks.testing.logging.*
import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    kotlin("jvm") version "1.3.40"
    `build-scan`
}

val agent: Configuration by configurations.creating

dependencies {
    agent(Libs.AspectJ.aspectjweaver)
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

tasks.withType(Wrapper::class) {
    gradleVersion = "5.4.1"
    distributionType = Wrapper.DistributionType.ALL
}

allprojects {
    group = "io.github.serpro69"
    version = "0.1"

    repositories {
        mavenCentral()
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            suppressWarnings = true
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        doFirst {
            jvmArgs("-javaagent:${agent.singleFile}")
        }

        // show standard out and standard error of the test JVM(s) on the console
        testLogging.showStandardStreams = true

        // Always run tests, even when nothing changed.
        dependsOn("cleanTest")

        testLogging {
            showStandardStreams = true
            events(
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT
            )
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true

            debug {
                events(
                    TestLogEvent.STARTED,
                    TestLogEvent.PASSED,
                    TestLogEvent.FAILED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_OUT,
                    TestLogEvent.STANDARD_ERROR
                )
                exceptionFormat = TestExceptionFormat.FULL
            }

            info.events = debug.events
            info.exceptionFormat = debug.exceptionFormat
        }
    }
}

