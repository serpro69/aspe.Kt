package io.github.serpro69.aspekt.core.aspect

import io.github.serpro69.aspekt.core.*
import io.github.serpro69.aspekt.core.TestHelper.expectedLogDetailsWithoutDuration
import io.github.serpro69.aspekt.core.TestHelper.logsDurationEntry
import io.github.serpro69.aspekt.core.TestHelper.logsWithoutDuration
import io.kotlintest.*
import io.kotlintest.matchers.collections.*
import io.kotlintest.matchers.string.*
import io.kotlintest.specs.*

class LogAspectTest : FreeSpec() {

    override fun beforeSpec(spec: Spec) {
        LogReader.cleanLogFile()
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        LogReader.cleanLogFile()
    }

    init {
        "GIVEN a kotlin function is annotated with Loggable" - {
            val testClass = TestClass()

            "WHEN function with parameters is invoked" - {
                testClass.sum(6, 8)

                "THEN invocation and return are logged" {
                    val logs = LogReader.readLogfile()

                    logsWithoutDuration(logs) shouldContainExactly expectedLogDetailsWithoutDuration(
                        "sum",
                        arrayOf(6, 8),
                        14
                    )

                    logsDurationEntry(logs) shouldMatch Regex("""Duration: \d+ nanos""")
                }
            }

            "WHEN function without parameters is invoked" - {
                testClass.getName()

                "THEN invocation and return are logged" {
                    val logs = LogReader.readLogfile()

                    logsWithoutDuration(logs) shouldContainExactly expectedLogDetailsWithoutDuration(
                        "getName",
                        emptyArray(),
                        "Deep Thought"
                    )

                    logsDurationEntry(logs) shouldMatch Regex("""Duration: \d+ nanos""")
                }
            }

            "WHEN logger is set to level.INFO and loggable annotation with logLevel.DEBUG" - {
                testClass.notLogged()

                "THEN invocation and return are not logged" {
                    val logs = LogReader.readLogfile()

                    logs shouldBe emptyList()
                }
            }

            "WHEN function return type is kotlin.Unit" - {
                testClass.returnUnit()

                "THEN invocation and return are logged" {
                    val logs = LogReader.readLogfile()

                    logsWithoutDuration(logs) shouldContainExactly expectedLogDetailsWithoutDuration(
                        "returnUnit",
                        emptyArray(),
                        null
                    )
                }
            }

            "WHEN logDuration == false" - {
                testClass.logDurationFalse()

                val logs = LogReader.readLogfile()
                "THEN invocation and return are logged" {

                    logsWithoutDuration(logs) shouldContainExactly expectedLogDetailsWithoutDuration(
                        "logDurationFalse",
                        emptyArray(),
                        null
                    )
                }

                "AND execution duration is not logged" {
                    logsDurationEntry(logs) shouldBe null
                }
            }

            "WHEN logResult == false" - {
                testClass.logReturnFalse()

                val logs = LogReader.readLogfile()
                "THEN invocation and params are logged and result is not logged" {

                    logsWithoutDuration(logs) shouldContainExactly expectedLogDetailsWithoutDuration(
                        "logReturnFalse",
                        emptyArray(),
                        null
                    ).dropLast(1)
                }
            }
        }
    }
}
