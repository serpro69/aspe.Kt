plugins {
    kotlin("jvm")
}

dependencies {
    runtime(Libs.AspectJ.aspectjweaver)
    compileOnly(Libs.AspectJ.aspectjrt)
    implementation(Libs.Kotlin.std8)
    implementation(Libs.Kotlin.reflect)
    runtime(Libs.groovy)
    runtime(Libs.Logging.Logback.logback_core)
    runtime(Libs.Logging.Logback.logback_classic)
    implementation(Libs.Logging.slf4j)
    testImplementation(Libs.Testing.kotlintest)
}

