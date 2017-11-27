package com.epam.core.webdriver;

import com.epam.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public abstract class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    WebDriver driver;

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
            LOGGER.debug("Driver quited");
        }
    }

    public void waitUntilVisible(WebElement element) {
        WaitUtil.untilVisible(element, driver);
    }
}

