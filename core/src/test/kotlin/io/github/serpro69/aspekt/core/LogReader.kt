package io.github.serpro69.aspekt.core

import java.io.*

internal object LogReader {
    private val logFilePath = "${System.getProperty("user.dir")}/logs/logfile.log"

    fun readLogfile(): List<String> {
        val fis = FileInputStream(logFilePath)
        return fis.bufferedReader().use { it.readLines() }
    }

    fun cleanLogFile() {
        val fos = FileOutputStream(logFilePath)
        fos.use { it.bufferedWriter().flush() }
    }
}