package io.github.serpro69.aspekt.core.aspect

import io.github.serpro69.aspekt.core.*
import io.kotlintest.matchers.collections.*
import io.kotlintest.specs.*

class LogAspectTest : FreeSpec({
    "GIVEN a kotlin function is annotated with Loggable" - {
        val testClass = TestClass()

        "WHEN function with parameters is invoked" - {
            testClass.sum(6, 8)

            "THEN invocation and return are logged" {
                val logs = LogReader.readLogfile()

                logs shouldContainExactly listOf(
                    "Invoked: 'sum'",
                    "Returning: '14'"
                )
            }
        }

        "WHEN function without parameters is invoked" - {
            testClass.getName()

            "THEN invocation and return are logged" {
                val logs = LogReader.readLogfile()

                logs shouldContainExactly listOf(
                    "Invoked: 'getName'",
                    "Returning: 'Deep Thought'"
                )
            }
        }

        "WHEN logger is set to level.INFO and loggable annotation with level.DEBUG" - {
            testClass.notLogged()

            "THEN invocation and return are not logged" {
                val logs = LogReader.readLogfile()

                logs shouldNotContain "Invoked: 'notLogged'"
            }
        }
    }
})
