package io.github.serpro69.aspekt

import io.github.serpro69.aspekt.annotation.*

internal class TestClass {

    @Loggable
    fun sum(x: Int, y: Int) = x + y
}
