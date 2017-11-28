package com.epam.tests;

import com.epam.core.Configuration;
import com.epam.core.guice.GuiceInjector;
import com.epam.core.webdriver.DriverManager;
import com.epam.utils.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.inject.Inject;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @Inject
    protected DriverManager driverManager;

    public BaseTest() {
        GuiceInjector.get().injectMembers(this);
    }

    @BeforeSuite
    public void openBrowser() {
        try {
            driverManager.open(Configuration.getBaseUrl());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @AfterSuite
    public void closeBrowser() {
        driverManager.quit();
    }

    protected void verifyLink(String title, String link) {
        if (link != null) {
            String failMessage = "%s link is unavailable: %s";
            int code = HttpUtil.getResponseCode(link);
            assertEquals(code, 200, format(failMessage, title, link));
        } else {
            String nullMessage = "Null link for %s was found";
            LOGGER.warn(format(nullMessage, title));
        }
    }
}
