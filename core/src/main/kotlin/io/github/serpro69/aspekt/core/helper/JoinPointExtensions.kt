package io.github.serpro69.aspekt.core.helper

import org.aspectj.lang.*
import org.aspectj.lang.reflect.*

internal val JoinPoint.method
    get() = (this.signature as MethodSignature).method
