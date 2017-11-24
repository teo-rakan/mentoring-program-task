package com.epam.core.guice.modules;

import com.epam.core.webdriver.DriverManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class GuiceModule extends AbstractModule {

    @Singleton
    @Provides
    public abstract DriverManager provideDriverManager();

    @Provides
    public abstract FieldDecorator provideFieldDecorator(DriverManager driverManager);

    @Override
    protected void configure() {

    }
}
