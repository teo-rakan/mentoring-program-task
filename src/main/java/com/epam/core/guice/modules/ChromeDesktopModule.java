package com.epam.core.guice.modules;

import com.epam.core.webdriver.ChromeDriverManager;
import com.epam.core.webdriver.DriverManager;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class ChromeDesktopModule extends GuiceModule {

    @Provides
    public DriverManager provideDriverManager() {
        return new ChromeDriverManager();
    }

    @Override
    public FieldDecorator provideFieldDecorator(DriverManager driverManager) {
        WebDriver driver = driverManager.getDriver();
        return new DefaultFieldDecorator(new DefaultElementLocatorFactory(driver));
    }

}
