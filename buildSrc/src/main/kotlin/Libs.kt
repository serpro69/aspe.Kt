
object Libs {

    object Kotlin {
        private const val ver = "1.3.40"

        const val std8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$ver"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$ver"
    }

    object AspectJ {
        private const val ver = "1.9.4"

        const val aspectjrt = "org.aspectj:aspectjrt:$ver"
        const val aspectjweaver = "org.aspectj:aspectjweaver:$ver"
    }

    object Testing {
        const val kotlintest = "io.kotlintest:kotlintest-runner-junit5:3.3.2"
    }

    object Logging {
        object Logback {
            private const val ver = "1.2.3"

            const val logback_core = "ch.qos.logback:logback-core:$ver"
            const val logback_classic = "ch.qos.logback:logback-classic:$ver"
        }

        const val slf4j = "org.slf4j:slf4j-api:1.7.26"
    }

    const val groovy = "org.codehaus.groovy:groovy:2.5.6"
}
