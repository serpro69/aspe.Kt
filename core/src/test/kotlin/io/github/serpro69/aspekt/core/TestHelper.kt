package io.github.serpro69.aspekt.core

internal object TestHelper {

    fun expectedLogDetailsWithoutDuration(
        name: String,
        parameters: Array<out Any>,
        returnValue: Any?
    ): List<String> {
        val sb = StringBuilder()
        sb.append("Invoked '$name'")
        sb.append("\nParameters: ${parameters.map { it::class.qualifiedName to it.toString() }}")
        if (returnValue != null) {
            sb.append("\nReturn: ${returnValue::class.qualifiedName to returnValue}")
        } else {
            sb.append("\nReturn: null")
        }
        return sb.toString().split("\n")
    }

    fun logsWithoutDuration(logs: List<String>) = logs.filter { !it.startsWith("Duration: ") }

    fun logsDurationEntry(logs: List<String>) = logs.find { it.startsWith("Duration:") }
}
