plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))
    runtime(Libs.AspectJ.aspectjweaver)
    implementation(Libs.Kotlin.std8)
    testImplementation(Libs.Testing.kotlintest)
}
