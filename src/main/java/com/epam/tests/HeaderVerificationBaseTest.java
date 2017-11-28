package com.epam.tests;

import com.epam.pages.bean.MenuItem;
import com.epam.utils.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HeaderVerificationBaseTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(HeaderVerificationBaseTest.class);

    protected void verifyMenuItems(List<MenuItem> actualHeaders,
                                   List<MenuItem> expectedHeaders) {
        String diffMessage = "Actual header count: %d Expected: %d";
        int actualHeaderCount = actualHeaders.size();
        int expectedHeaderCount = expectedHeaders.size();

        assertEquals(actualHeaderCount, expectedHeaderCount,
                format(diffMessage, actualHeaderCount, expectedHeaderCount));

        for (int i = 0; i < actualHeaders.size(); i++) {
            MenuItem actualHeader = actualHeaders.get(i);
            MenuItem expectedHeader = expectedHeaders.get(i);
            verifyMenuItem(actualHeader, expectedHeader);
        }
    }

    protected void verifyMenuItem(MenuItem actual, MenuItem expected) {
        String diffMessage = "\nActual: %s\nExpected: %s";

        assertTrue(actual.equals(expected), format(diffMessage, actual, expected));
        verifyLink(actual.getTitle(), actual.getLink());
    }
}
