package com.example.finalproject_jacksversion;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Log {
    public void SimpleFormatter(){
        String.format("%4$s: %5$s [%1$tc]%n");
    }
    private final Logger logger;
    private final FileHandler fileHandler;

    public Log(int day, String logDirectory) throws IOException {
        File directory = new File(logDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
        logger = Logger.getLogger(Controller.class.getName() + "_Day" + day);
        try {
            fileHandler = new FileHandler(logDirectory + "/log_Day" + day + ".txt");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void info(String msg) {
        logger.info(msg);
    }
}

