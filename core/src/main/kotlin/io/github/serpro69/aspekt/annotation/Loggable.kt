package io.github.serpro69.aspekt.annotation

import io.github.serpro69.aspekt.annotation.Loggable.*

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
    val loggerName: String = ""
) {

    enum class LogLevel(private val level: Int) {
        TRACE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4)
    }
}