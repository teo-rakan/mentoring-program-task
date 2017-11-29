package com.epam.core.guice.modules;

import com.epam.core.webdriver.DriverFactory;
import com.epam.core.webdriver.DriverManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.pagefactory.FieldDecorator;


public abstract class GuiceModule extends AbstractModule {

    private static final Logger LOGGER = LogManager.getLogger(GuiceModule.class);

    @Provides
    public abstract DriverFactory provideDriverFactory();

    @Singleton
    @Provides
    ThreadLocal<DriverManager> provideThreadDriverManager(DriverFactory driverFactory) {
        return ThreadLocal.withInitial(() -> new DriverManager(driverFactory));
    }

    @Provides
    public DriverManager provideDriverManager(ThreadLocal<DriverManager> threadDriverManager) {
        LOGGER.debug("DriverManager was provided");
        return threadDriverManager.get();
    }

    @Provides
    public abstract FieldDecorator provideFieldDecorator(DriverManager driverManager);

    @Override
    protected void configure() {

    }
}
