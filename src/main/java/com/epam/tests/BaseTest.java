package com.epam.tests;

import com.epam.core.guice.GuiceInjector;
import com.epam.core.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import javax.inject.Inject;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @Inject
    DriverManager driverManager;

    @BeforeClass(description = "Inject members")
    public void inject() {
        LOGGER.debug("Try to inject DriverManager to test class");
        GuiceInjector.get().injectMembers(this);

        LOGGER.debug("Open home page");
        try {
            driverManager.open("http://www.sho.com/");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
