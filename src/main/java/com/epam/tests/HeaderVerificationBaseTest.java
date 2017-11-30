package com.epam.tests;

import com.epam.pages.entity.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static java.lang.String.format;

public class HeaderVerificationBaseTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(HeaderVerificationBaseTest.class);

    protected boolean verifyMenuItems(List<MenuItem> actualHeaders,
                                      List<MenuItem> expectedHeaders) {
        String diffMessage = "Actual header count: %d Expected: %d";
        int actualHeaderCount = actualHeaders.size();
        int expectedHeaderCount = expectedHeaders.size();
        boolean match = true;

        if (actualHeaderCount != expectedHeaderCount) {
            LOGGER.error(
                    format(diffMessage, actualHeaderCount, expectedHeaderCount));
            LOGGER.info("Expected:");
            expectedHeaders.stream().forEach(LOGGER::info);
            LOGGER.info("Found:");
            actualHeaders.stream().forEach(LOGGER::info);
            return false;
        }

        for (int i = 0; i < actualHeaderCount; i++) {
            MenuItem actualHeader = actualHeaders.get(i);
            MenuItem expectedHeader = expectedHeaders.get(i);
            match &= verifyMenuItem(actualHeader, expectedHeader);
        }
        return match;
    }

    protected boolean verifyMenuItem(MenuItem actual, MenuItem expected) {
        String diffMessage = "Actual menu item: %s Expected: %s";
        boolean areItemsEqual = actual.equals(expected);
        if (!areItemsEqual)
            LOGGER.error(format(diffMessage, actual, expected));

        return areItemsEqual && verifyLink(actual.getTitle(), actual.getLink());
    }
}
