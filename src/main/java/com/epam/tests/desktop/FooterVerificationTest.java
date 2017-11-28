package com.epam.tests.desktop;

import com.epam.core.Configuration;
import com.epam.pages.desktop.FooterLayout;
import com.epam.tests.BaseTest;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FooterVerificationTest extends BaseTest {

    @Test
    public void verifyFooter() {
        driverManager.scrollToThePageBottom();
        FooterLayout footer = new FooterLayout();

        assertTrue(footer.isLogoVisible(), "Footer logo isn't visible");
        assertTrue(footer.isHaveShowtimeHeaderVisible(),
                "'Have Showtime?' footer header isn't visible");
        assertTrue(footer.isWantShowtimeSubscribeHeaderVisible(),
                "'Want Showtime? Subscribe.' footer header isn't visible");
        verifyStreamShowtimeLink(footer.getStreamShowtimeLink());
        verifyTryItNowForFreeLink(footer.getTryItNowForFreeLink());
    }

    private void verifyStreamShowtimeLink(String actualLink) {
        String failMessage = "Actual link: %s Expected: %s";
        String currentUrl = driverManager.getCurrentUrl();
        String validCurrentUrl = currentUrl.contains("#")
                ? currentUrl.substring(0, currentUrl.indexOf("#"))
                : currentUrl;
        String expectedLink = validCurrentUrl + "#/stream/home/int-default-3671";

        assertEquals(actualLink, expectedLink,
                format(failMessage, actualLink, expectedLink));
        verifyLink("Stream showtime", actualLink);
    }

    private void verifyTryItNowForFreeLink(String actualLink) {
        String failMessage = "Actual link: %s Expected that link will be start with: %s";
        String baseUrl = Configuration.getBaseUrl();
        String expectedLink = baseUrl + "/order";

        assertTrue(actualLink.startsWith(expectedLink),
                format(failMessage, actualLink, expectedLink));
        verifyLink("Try it now for free?", actualLink);
    }
}
