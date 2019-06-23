package io.github.serpro69.aspekt.aspect

import org.aspectj.lang.*
import org.aspectj.lang.annotation.*
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.*

@Aspect
class LogAspect {

    /**
     * Pointcut for join points representing any function or constructor initialization
     * that is annotated with [io.github.serpro69.aspekt.annotation.Loggable].
     */
    @Pointcut("""
            (execution(* *(..)) || initialization(*.new(..)))
                && @annotation(io.github.serpro69.aspekt.annotation.Loggable)
    """)
    fun wrapFunction() {
    }

    /**
     * Logs function invocation, arguments and return type/value.
     */
    @Around("wrapFunction()")
    fun logFunction(jp: ProceedingJoinPoint): Any {
        val method = (jp.signature as MethodSignature).method

        println("Invoked function: ${method.name}")

        return jp.proceed()
    }
}