package com.epam.core.webdriver;

import com.epam.core.guice.GuiceInjector;
import com.epam.utils.ScreenshotUtil;
import com.epam.utils.WaitUtil;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;


public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    private static ThreadLocal<DriverManager> THREAD_LOCAL =
            ThreadLocal.withInitial(DriverManager::new);
    private final String SCROLL_FAILED = "Cannot scroll to %s: %s";
    @Inject
    private DriverFactory driverFactory;
    private WebDriver driver;

    private DriverManager() {
        GuiceInjector.get().injectMembers(this);
    }

    public static DriverManager getInstance() {
        return THREAD_LOCAL.get();
    }

    public WebDriver getDriver() {
        if (null == driver) {
            driver = driverFactory.createDriver();
            LOGGER.debug("Driver was successfully created");
        }
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
            LOGGER.debug("Driver successfully quited");
        }
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void waitUntilVisible(WebElement element) {
        WaitUtil.untilVisible(element, getDriver());
    }

    public void scrollToThePageBottom() {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) getDriver());
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception e) {
            LOGGER.error(format(SCROLL_FAILED, " the page bottom", e.getMessage()));
        }
    }

    public void scrollTo(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            LOGGER.error(format(SCROLL_FAILED, element, e.getMessage()));
        }
    }

    public void takeScreenshot() {
        ScreenshotUtil.take(getDriver());
    }
}

