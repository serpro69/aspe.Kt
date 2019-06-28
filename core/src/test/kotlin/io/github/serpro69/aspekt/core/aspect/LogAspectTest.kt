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

            "WHEN logger is set to level.INFO and loggable annotation with level.DEBUG" - {
                testClass.notLogged()

                "THEN invocation and return are not logged" {
                    val logs = LogReader.readLogfile()

                    logs shouldBe emptyList()
                }
            }
        }
    }
}
