package io.github.serpro69.aspekt.core.annotation

import io.github.serpro69.aspekt.core.annotation.Loggable.*

/**
 * Logs invocation and return details of annotated kotlin functions and java methods.
 *
 * @property logLevel the logging level to use. Defaults to [LogLevel.INFO].
 * @property loggerName a custom name for logger. By default will use the declaring class of the function/method.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Loggable(
    val logLevel: LogLevel = LogLevel.INFO,
    val loggerName: String = "",
    val logDuration: Boolean = true,
    val logResult: Boolean = true
) {

    enum class LogLevel {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}
