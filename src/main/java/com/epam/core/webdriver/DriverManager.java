package com.epam.core.webdriver;

import com.epam.core.guice.GuiceInjector;
import com.epam.utils.WaitUtil;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    private WebDriver driver;

    public DriverManager(DriverFactory driverFactory) {
        LOGGER.debug("New DriverManager creating...");
        driver = driverFactory.createDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

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

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void waitUntilVisible(WebElement element) {
        WaitUtil.untilVisible(element, driver);
    }

    public void scrollToThePageBottom() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}

