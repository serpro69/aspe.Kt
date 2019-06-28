package io.github.serpro69.aspekt.core.helper

import io.github.serpro69.aspekt.core.annotation.*
import org.aspectj.lang.*
import org.slf4j.*
import java.lang.reflect.*

internal object LogHelper {
    private val loggers = HashMap<String, Logger>()
    private val annotation = { method: Method -> method.getAnnotation(Loggable::class.java) }

    fun log(jp: ProceedingJoinPoint, msg: (ProceedingJoinPoint, Method, Loggable) -> String) {
        val method = jp.method
        val annotation = annotation(method)
        val logger = getLogger(method)

        when (annotation.logLevel) {
            Loggable.LogLevel.TRACE -> if (logger.isTraceEnabled) logger.trace(msg.invoke(jp, method, annotation))
            Loggable.LogLevel.DEBUG -> if (logger.isDebugEnabled) logger.debug(msg.invoke(jp, method, annotation))
            Loggable.LogLevel.INFO -> if (logger.isInfoEnabled) logger.info(msg.invoke(jp, method, annotation))
            Loggable.LogLevel.WARN -> if (logger.isWarnEnabled) logger.warn(msg.invoke(jp, method, annotation))
            Loggable.LogLevel.ERROR -> if (logger.isErrorEnabled) logger.error(msg.invoke(jp, method, annotation))
        }
    }

    private fun getLogger(method: Method): Logger {
        return when (val loggerName = annotation(method).loggerName) {
            "" -> {
                method.declaringClass?.let { cls: Class<out Any> ->
                    computeLogger(cls.name)
                } ?: computeLogger("Loggable")
            }
            else -> computeLogger(loggerName)
        }
    }

    private fun computeLogger(name: String) = loggers.computeIfAbsent(name) { LoggerFactory.getLogger(it) }
}