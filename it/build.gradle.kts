plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))
    runtime(Libs.AspectJ.aspectjweaver)
    implementation(Libs.Kotlin.std8)
    testImplementation(Libs.Testing.kotlintest)
    runtime(Libs.groovy)
    runtime(Libs.Logging.Logback.logback_core)
    runtime(Libs.Logging.Logback.logback_classic)
}
