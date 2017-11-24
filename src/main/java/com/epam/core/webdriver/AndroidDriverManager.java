package com.epam.core.webdriver;

import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager extends MobileDriverManager {

    private static final Logger LOGGER = LogManager.getLogger(AndroidDriverManager.class);

    @Override
    void createDriver() {
        DesiredCapabilities capabilities = loadCapabilitiesFromProperties("android");
        String url = System.getProperty("appium.url");

        try {
            driver = new AndroidDriver<>(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            LOGGER.error("Malformed URL: " + url);
        }
    }
}
