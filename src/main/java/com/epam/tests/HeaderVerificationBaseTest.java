package com.epam.tests;

import com.epam.pages.bean.MenuItem;
import com.epam.utils.HttpUtil;
import org.testng.Assert;

import java.util.List;

public class HeaderVerificationBaseTest extends BaseTest {

    protected void verifyMenuItems(List<MenuItem> actualHeaders,
                                   List<MenuItem> expectedHeaders) {
        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size(),
                "Expected header count: " + expectedHeaders.size()
                        + " Found: " + actualHeaders.size());

        for (int i = 0; i < actualHeaders.size(); i++) {
            MenuItem actualHeader = actualHeaders.get(i);
            MenuItem expectedHeader = expectedHeaders.get(i);
            verifyMenuItem(actualHeader, expectedHeader);
        }
    }

    protected void verifyMenuItem(MenuItem actual, MenuItem expected) {
        String link = actual.getLink();
        int responseCode = HttpUtil.getStatusCode(link);

        Assert.assertTrue(actual.equals(expected),
                "\nActual: " + actual + "\nExpected: " + expected);
        Assert.assertEquals(responseCode, 200,
                actual.getTitle() + " link is unavailable: " + link);
    }

}
