package com.epam.core.webdriver;

import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSDriverFactory extends DriverFactory {

    private static final Logger LOGGER = LogManager.getLogger(IOSDriverFactory.class);

    @Override
    WebDriver createDriver() {
        WebDriver driver = null;
        DesiredCapabilities capabilities = loadCapabilitiesFromProperties("ios");
        String url = System.getProperty("appium.url");

        try {
            driver = new IOSDriver<>(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            LOGGER.error("Malformed URL: " + url);
        }
        return driver;
    }
}
