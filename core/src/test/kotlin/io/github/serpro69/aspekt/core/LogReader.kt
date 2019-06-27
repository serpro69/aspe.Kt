package io.github.serpro69.aspekt.core

import java.io.*

internal object LogReader {

    fun readLogfile(lines: Int = 2): List<String> {
        val fis = FileInputStream("${System.getProperty("user.dir")}/logs/logfile.log")
        return fis.bufferedReader().use { it.readLines().takeLast(lines) }
    }
}