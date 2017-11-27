package com.epam.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private static final Logger LOGGER = LogManager.getLogger(WaitUtil.class);

    private final static int TIMEOUT_SEC = 20;

    public static void untilVisible(WebElement element, WebDriver driver) {
        LOGGER.debug("Wait for visibility of " + element);
        new WebDriverWait(driver, TIMEOUT_SEC)
                .until(ExpectedConditions.visibilityOf(element));
    }

}
