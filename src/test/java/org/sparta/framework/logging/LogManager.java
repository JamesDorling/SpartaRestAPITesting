package org.sparta.framework.logging;

import java.io.IOException;
import java.util.logging.*;

public class LogManager {

    private static final String fullLoggerLocation = "src/test/resources/logs/full_log.log";
    private static final String mainLoggerLocation = "src/test/resources/logs/main_log.log";
    private static boolean loggerExists = false;
    private static final Logger logger = Logger.getLogger("myLogger");

    private static void createLogger() {
        loggerExists = true;
        logger.setUseParentHandlers(false);

        try {
            // mainHandler handles all of the noteworthy logs
            Handler mainHandler = new FileHandler(mainLoggerLocation, true);
            mainHandler.setLevel(Level.CONFIG);
            mainHandler.setFormatter(new LogFormat());
            logger.addHandler(mainHandler);
            Handler fullHandler = new FileHandler(fullLoggerLocation, true);
            fullHandler.setLevel(Level.FINEST);
            fullHandler.setFormatter(new LogFormat());
            logger.addHandler(fullHandler);
            logger.log(Level.INFO, "Loggers created!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
    }

    public static void writeLog(Level level, String message) {
        if (!loggerExists) {
            createLogger();
        }
        logger.setLevel(level);
        logger.log(level, message);
    }

}
