package com.epam.core.guice.modules;

import com.epam.core.webdriver.AndroidDriverManager;
import com.epam.core.webdriver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class AndroidModule extends GuiceModule {

    private static final Logger LOGGER = LogManager.getLogger(AndroidModule.class);

    @Override
    public DriverManager provideDriverManager() {
        LOGGER.debug("AndroidDriverManager was provided");
        return new AndroidDriverManager();
    }

    @Override
    public FieldDecorator provideFieldDecorator(DriverManager driverManager) {
        LOGGER.debug("AppiumFieldDecorator was provided");
        return new AppiumFieldDecorator(driverManager.getDriver());
    }
}
