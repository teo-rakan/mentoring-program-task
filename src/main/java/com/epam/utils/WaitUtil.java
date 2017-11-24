package com.epam.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private final static int TIMEOUT_SEC = 20;

    public static void untilVisible(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, TIMEOUT_SEC)
                .until(ExpectedConditions.visibilityOf(element));
    }

}
