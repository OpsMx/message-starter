package com.opsmx.messaging.logging;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class LogService {
    public static void logMessage(Level level, String messageTemplate, Object... placeholders) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String className = stackTraceElements[2].getClassName();
        String formattedMessage = String.format(messageTemplate, placeholders);
        Logger logger = LogManager.getLogger(className);
        logger.log(level, formattedMessage);
    }
}
