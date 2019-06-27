scan("30 seconds")
def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"

appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "[%t] [%-5level] %date{ISO8601} %c{0} %ex - %msg%n"
    }
}

appender("File-Appender", FileAppender) {
    file = "${LOG_PATH}/logfile.log"

    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
        outputPatternAsHeader = true
    }
}

root(INFO, ["Console-Appender"])
logger("io.github.serpro69.aspekt.core", INFO, ["Console-Appender", "File-Appender"], false)
