package io.github.serpro69.aspekt.core.aspect

import io.github.serpro69.aspekt.core.helper.LogHelper.log
import org.aspectj.lang.*
import org.aspectj.lang.annotation.*
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.*

@Suppress("unused")
@Aspect
object LogAspect {

    /**
     * Pointcut for any function invocation.
     */
    @Pointcut("execution(* *(..))")
    fun anyFunction(){
    }

    /**
     * Pointcut for elements annotated with [io.github.serpro69.aspekt.annotation.Loggable].
     */
    @Pointcut("@annotation(io.github.serpro69.aspekt.core.annotation.Loggable)")
    fun loggableAnnotation() {
    }

    /**
     * Logs function invocation, arguments and return type/value.
     */
    @Around("anyFunction() && loggableAnnotation()")
    fun logFunction(jp: ProceedingJoinPoint): Any {
        val method = (jp.signature as MethodSignature).method

        log(method) { "Invoked: '${method.name}'" }

        val returnValue = jp.proceed()

        log(method) { "Returning: '$returnValue'" }

        return returnValue
    }
}