package com.epam.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaitUtil {

    private final static int TIMEOUT_SEC = 20;
    private final static int POLLING_DURATION_MILLISEC = 100;

    public static void untilVisible(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, TIMEOUT_SEC)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void untilInvisible(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, TIMEOUT_SEC)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    private static void waitUntil(Function<? super WebDriver, Boolean> condition, WebDriver driver) {
        new FluentWait<>(driver)
                .withTimeout(TIMEOUT_SEC, SECONDS)
                .pollingEvery(POLLING_DURATION_MILLISEC, MILLISECONDS)
                .until(condition);
    }
}
