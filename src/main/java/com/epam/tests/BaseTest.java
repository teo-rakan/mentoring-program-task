package com.epam.tests;

import com.epam.core.Configuration;
import com.epam.core.guice.GuiceInjector;
import com.epam.core.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.inject.Inject;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @Inject
    DriverManager driverManager;

    @BeforeClass
    public void injectMembers() {
        GuiceInjector.get().injectMembers(this);
    }

    @BeforeClass(dependsOnMethods = "injectMembers")
    public void openBrowser() {
        LOGGER.debug("Open home page");
        try {
            driverManager.open(Configuration.getBaseUrl());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @AfterClass
    public void closeBrowser() {
        driverManager.quit();
    }
}
