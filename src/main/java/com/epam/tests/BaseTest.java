package com.epam.tests;

import com.epam.core.Configuration;
import com.epam.core.guice.GuiceInjector;
import com.epam.core.webdriver.DriverManager;
import com.epam.utils.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.inject.Inject;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @Inject
    protected DriverManager driverManager;

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

    protected void verifyLink(String title, String link) {
        if (link != null) {
            String linkMessage = "%s link is unavailable: %s";
            int responseCode = HttpUtil.getResponseCode(link);

            assertEquals(responseCode, 200, format(linkMessage, title, link));
        } else {
            LOGGER.warn("Null link for " + title + " was found.");
        }
    }
}
