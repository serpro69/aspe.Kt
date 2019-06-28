package io.github.serpro69.aspekt.core.aspect

import io.github.serpro69.aspekt.core.helper.LogHelper.log
import org.aspectj.lang.*
import org.aspectj.lang.annotation.*

@Suppress("unused")
@Aspect
object LogAspect {

    /**
     * Pointcut for any function invocation.
     */
    @Pointcut("execution(* *(..))")
    fun anyFunction() {
    }

    /**
     * Pointcut for elements annotated with [io.github.serpro69.aspekt.core.annotation.Loggable].
     */
    @Pointcut("@annotation(io.github.serpro69.aspekt.core.annotation.Loggable)")
    fun loggableAnnotation() {
    }

    /**
     * Logs function invocation, arguments and return type/value.
     */
    @Around("anyFunction() && loggableAnnotation()")
    fun logFunction(pjp: ProceedingJoinPoint): Any? {
        val start = System.nanoTime()
        val returnValue = pjp.proceed()
        val end = System.nanoTime()

        log(pjp) { jp, method, annotation ->
            val sb = StringBuilder()

            sb.append("Invoked '${method.name}'")

            // Log arguments
            val args = jp.args.map { it::class.qualifiedName to it.toString() }
            if (args.isNotEmpty()) sb.append("\nParameters: $args") else sb.append("\nParameters: []")

            // Log execution duration in nanos
            if (annotation.logDuration) sb.append("\nDuration: ${end - start} nanos")

            // Log return value
            if (annotation.logResult) sb.append("\nReturn: ${returnValue::class.qualifiedName to returnValue}")

            // TODO: 28.06.19 should we log execution of around advice (if debug.isEnabled) to count overhead?
            sb.toString()
        }

        return returnValue
    }
}