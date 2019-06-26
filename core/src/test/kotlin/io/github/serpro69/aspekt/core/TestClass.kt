package io.github.serpro69.aspekt.core

import io.github.serpro69.aspekt.core.annotation.*

internal class TestClass {

    @Loggable
    fun sum(x: Int, y: Int) = x + y
}
