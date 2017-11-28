package com.epam.core.guice.modules;

import com.epam.core.webdriver.ChromeDriverManager;
import com.epam.core.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class WindowsChromeModule extends GuiceModule {

    private static final Logger LOGGER = LogManager.getLogger(WindowsChromeModule.class);

    public WindowsChromeModule() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @Override
    public DriverManager provideDriverManager() {
        LOGGER.debug("ChromeDriverManager was provided");
        return new ChromeDriverManager();
    }

    @Override
    public FieldDecorator provideFieldDecorator(DriverManager driverManager) {
        LOGGER.debug("DefaultFieldDecorator was provided");
        WebDriver driver = driverManager.getDriver();
        return new DefaultFieldDecorator(new DefaultElementLocatorFactory(driver));
    }
}
