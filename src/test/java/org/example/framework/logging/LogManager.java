package org.example.framework.logging;

import java.io.IOException;
import java.util.logging.*;

public class LogManager {

    private static final String fileLocation = "src/test/java/resources/log_file.log";
    private static boolean loggerExists = false;
    private static final Logger logger = Logger.getLogger("myLogger");

    public static void createLogger(Level level) {
        loggerExists = true;
        logger.setUseParentHandlers(false);

        try {
            Handler fileHandler = new FileHandler(fileLocation, true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new LogFormat());
            logger.log(Level.INFO, "Logger created!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(level);
        logger.addHandler(consoleHandler);
    }

    public static void writeLog(Level level, String message) {
        if(loggerExists) {
            logger.setLevel(level);
            logger.log(level, message);
        }
    }

}
