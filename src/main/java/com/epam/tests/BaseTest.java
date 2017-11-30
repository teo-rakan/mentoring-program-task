package com.epam.tests;

import com.epam.core.guice.GuiceInjector;
import com.epam.core.webdriver.DriverManager;
import com.epam.utils.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import static java.lang.String.format;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @Inject
    protected DriverManager driverManager;

    public BaseTest() {
        GuiceInjector.get().injectMembers(this);
    }

    protected boolean verifyLink(String title, String link) {
        if (link == null) {
            String nullMessage = "Null link for %s was found";
            LOGGER.warn(format(nullMessage, title));
            return true; //todo CanBeNull flag
        }
        boolean isLinkAvailable = 200 == HttpUtil.getResponseCode(link);

        if (!isLinkAvailable)
            LOGGER.error("%s link is unavailable: %s", title, link);
        return isLinkAvailable;
    }
}
