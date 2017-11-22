package com.epam;

import com.epam.utils.PropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DesktopRunner {

    private static final Logger LOGGER = LogManager.getLogger(DesktopRunner.class);

    public static void main(String[] args) {
        LOGGER.info("Desktop Runner");
        setSystemProperties();
        LOGGER.info("Browser: " + System.getProperty("browser.name"));
    }

    static void setSystemProperties() {
        System.setProperty("browser.name", PropertyManager.get("browser.name"));
    }
}
