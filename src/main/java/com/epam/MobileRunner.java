package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobileRunner extends DesktopRunner {

    private static final Logger LOGGER = LogManager.getLogger(MobileRunner.class);

    public static void main(String[] args) {
        LOGGER.info("Mobile Runner");
        setSystemProperties();
        LOGGER.info("Browser: " + System.getProperty("browser.name"));
    }
}
