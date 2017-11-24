package com.epam.core.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public abstract class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    protected WebDriver driver;

    public WebDriver getDriver() {
        if (null == driver) {
            createDriver();
        }
        return driver;
    }

    abstract void createDriver();

    public void open(String url) {
        getDriver().get(url);
        LOGGER.info("Open URL: " + url);
    }

    public void quit() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

}

