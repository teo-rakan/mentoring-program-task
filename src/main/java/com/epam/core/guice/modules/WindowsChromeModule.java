package com.epam.core.guice.modules;

import com.epam.core.webdriver.ChromeDriverFactory;
import com.epam.core.webdriver.DriverFactory;
import com.epam.core.webdriver.DriverManager;
import com.google.inject.Provides;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;


public class WindowsChromeModule extends GuiceModule {

    private static final Logger LOGGER = LogManager.getLogger(WindowsChromeModule.class);

    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";

    @Provides
    public String provideChromeDriverPath() {
        LOGGER.debug("Chrome driver path: " + CHROME_DRIVER_PATH);
        return CHROME_DRIVER_PATH;
    }

    @Override
    public DriverFactory provideDriverFactory() {
        LOGGER.debug("ChromeDriverFactory was provided");
        return new ChromeDriverFactory();
    }

    @Override
    public FieldDecorator provideFieldDecorator(DriverManager driverManager) {
        LOGGER.debug("DefaultFieldDecorator was provided");
        WebDriver driver = driverManager.getDriver();
        return new DefaultFieldDecorator(new DefaultElementLocatorFactory(driver));
    }
}
