package com.epam.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    static final Logger LOGGER = LogManager.getLogger(ScreenshotUtil.class);

    private static final String screensFolder = "screenshots";

    public static void take(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = screensFolder + "/scr_" + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            LOGGER.info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            LOGGER.error("Failed to make screenshot");
        }
    }
}
