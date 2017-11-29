package com.epam.core.guice.modules;

import com.epam.core.webdriver.AndroidDriverFactory;
import com.epam.core.webdriver.DriverFactory;
import com.epam.core.webdriver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class AndroidChromeModule extends GuiceModule {

    private static final Logger LOGGER = LogManager.getLogger(AndroidChromeModule.class);

    @Override
    public DriverFactory provideDriverFactory() {
        LOGGER.debug("AndroidDriverFactory was provided");
        return new AndroidDriverFactory();
    }

    @Override
    public FieldDecorator provideFieldDecorator(DriverManager driverManager) {
        LOGGER.debug("AppiumFieldDecorator was provided");
        return new AppiumFieldDecorator(driverManager.getDriver());
    }
}
