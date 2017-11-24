package com.epam.core.guice.modules;

import com.epam.core.webdriver.AndroidDriverManager;
import com.epam.core.webdriver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class AndroidModule extends GuiceModule {

    @Override
    public DriverManager provideDriverManager() {
        return new AndroidDriverManager();
    }

    @Override
    public FieldDecorator provideFieldDecorator(DriverManager driverManager) {
        return new AppiumFieldDecorator(driverManager.getDriver());
    }
}
