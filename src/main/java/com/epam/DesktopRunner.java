package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DesktopRunner extends AbstractRunner {

    private static final Logger LOGGER = LogManager.getLogger(DesktopRunner.class);

    public static void main(String[] args) {
        LOGGER.info("Desktop Runner");
        setSystemProperties();
        LOGGER.info("Browser: " + System.getProperty("browser.name"));
    }
}
