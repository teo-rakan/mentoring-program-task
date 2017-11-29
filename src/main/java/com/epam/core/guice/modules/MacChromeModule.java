package com.epam.core.guice.modules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MacChromeModule extends WindowsChromeModule {

    private static final Logger LOGGER = LogManager.getLogger(MacChromeModule.class);
    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver";

    @Override
    public String provideChromeDriverPath() {
        LOGGER.debug("Chrome driver path: " + CHROME_DRIVER_PATH);
        return CHROME_DRIVER_PATH;
    }
}
