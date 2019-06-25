plugins {
    kotlin("jvm")
}

dependencies {
    runtime(Libs.AspectJ.aspectjweaver)
    compileOnly(Libs.AspectJ.aspectjrt)
    implementation(Libs.Kotlin.std8)
    testImplementation(Libs.Testing.kotlintest)
}

