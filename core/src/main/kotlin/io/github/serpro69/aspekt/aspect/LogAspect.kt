package io.github.serpro69.aspekt.aspect

import org.aspectj.lang.*
import org.aspectj.lang.annotation.*
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.*
import org.slf4j.*

@Suppress("unused")
@Aspect
object LogAspect {
    private val logger = HashMap<Class<*>, Logger>()

    private fun log(cls: Class<*>, message: () -> String) {
        val log = logger.computeIfAbsent(cls) { LoggerFactory.getLogger(it) }
        val msg = message.invoke()

        when {
            log.isTraceEnabled -> log.trace(msg)
            log.isDebugEnabled -> log.debug(msg)
            log.isInfoEnabled -> log.info(msg)
            log.isWarnEnabled -> log.warn(msg)
            log.isErrorEnabled -> log.error(msg)
        }
    }

    /**
     * Pointcut for any function invokation.
     */
    @Pointcut("execution(* *(..))")
    fun anyFunction(){
    }

    /**
     * Pointcut for elements annotated with [io.github.serpro69.aspekt.annotation.Loggable].
     */
    @Pointcut("@annotation(io.github.serpro69.aspekt.annotation.Loggable)")
    fun loggableAnnotation() {
    }

    /**
     * Logs function invocation, arguments and return type/value.
     */
    @Around("anyFunction() && loggableAnnotation()")
    fun logFunction(jp: ProceedingJoinPoint): Any {
        val method = (jp.signature as MethodSignature).method
        val cls = method.declaringClass

        log(cls) { "Invoked function '${method.name}' from class: '$cls'" }

        val returnValue = jp.proceed()

        log(cls) { "Returning: $returnValue" }

        return returnValue
    }
}