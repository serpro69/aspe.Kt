package io.github.serpro69.aspekt.aspect

import io.github.serpro69.aspekt.*
import io.kotlintest.specs.*

class LogAspectTest : FreeSpec({
    "GIVEN a kotlin function is annotated with Loggable" - {
        val testClass = TestClass()

        "THEN function invocation is logged" {
            testClass.sum(6, 8)
        }
    }
})
