package com.epam.core.webdriver;

import com.epam.utils.ScreenshotUtil;
import com.epam.utils.WaitUtil;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    private DriverFactory driverFactory;
    private WebDriver driver;

    public DriverManager(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    public WebDriver getDriver() {
        if (null == driver) {
            synchronized (this) {
                if (null == driver) {
                    driver = driverFactory.createDriver();
                    LOGGER.debug("Driver was successfully created");
                }
            }
        }
        return driver;
    }

    public void open(String url) {
        getDriver().get(url);
        LOGGER.info("Open URL: " + url);
    }

    public void quit() {
        if (null != driver) {
            synchronized (this) {
                if (null != driver) {
                    driver.quit();
                    driver = null;
                    LOGGER.debug("Driver successfully quited");
                }
            }
        }
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void waitUntilVisible(WebElement element) {
        WaitUtil.untilVisible(element, getDriver());
    }

    public void scrollToThePageBottom() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void takeScreenshot() {
        ScreenshotUtil.take(getDriver());
    }
}

