package com.epam.tests.desktop;

import com.epam.core.Configuration;
import com.epam.pages.desktop.FooterLayout;
import com.epam.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.lang.String.format;

public class FooterVerificationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(FooterVerificationTest.class);
    private static final String TRY_IT_NOW_FOR_FREE = "Try it now for free?";
    private static final String STREAM_SHOWTIME = "Stream showtime";
    private static final String HAVE_SHOWTIME_HEADER = "Have Showtime?";
    private static final String WANT_SHOWTIME_HEADER = "Want Showtime? Subscribe.";

    @Test
    public void verifyFooter() {
        SoftAssert softAssert = new SoftAssert();
        String logoFailMessage = "Footer logo isn't visible";
        String headerFailMessage = "'%s' footer header isn't visible";
        String linkFailMessage = "Link for '%s' has problems. See log";

        driverManager.scrollToThePageBottom();
        FooterLayout footer = new FooterLayout();

        softAssert.assertTrue(footer.isLogoVisible(), logoFailMessage);
        softAssert.assertTrue(footer.isHaveShowtimeHeaderVisible(),
                format(headerFailMessage, HAVE_SHOWTIME_HEADER));
        softAssert.assertTrue(footer.isWantShowtimeSubscribeHeaderVisible(),
                format(headerFailMessage, WANT_SHOWTIME_HEADER));
        softAssert.assertTrue(
                verifyStreamShowtimeLink(footer.getStreamShowtimeLink()),
                format(linkFailMessage, STREAM_SHOWTIME));
        softAssert.assertTrue(
                verifyTryItNowForFreeLink(footer.getTryItNowForFreeLink()),
                format(linkFailMessage, TRY_IT_NOW_FOR_FREE));
        softAssert.assertAll();
    }

    private boolean verifyStreamShowtimeLink(String actualLink) {
        String failMessage = "Actual link: %s Expected: %s";
        String currentUrl = driverManager.getCurrentUrl();
        String validCurrentUrl = currentUrl.contains("#")
                ? currentUrl.substring(0, currentUrl.indexOf("#"))
                : currentUrl;
        String expectedLink = validCurrentUrl + "#/stream/home/int-default-3671";

        if (!actualLink.equals(expectedLink)) {
            LOGGER.error(format(failMessage, actualLink, expectedLink));
            return false;
        } else
            return verifyLink(STREAM_SHOWTIME, actualLink);
    }

    private boolean verifyTryItNowForFreeLink(String actualLink) {
        String failMessage = "Actual link: %s Expected that link will be start with: %s";
        String baseUrl = Configuration.getBaseUrl();
        String expectedLink = baseUrl + "/order";

        if (!actualLink.startsWith(expectedLink)) {
            LOGGER.error(format(failMessage, actualLink, expectedLink));
            return false;
        } else
            return verifyLink(TRY_IT_NOW_FOR_FREE, actualLink);
    }
}
