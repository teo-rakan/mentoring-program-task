package com.epam.tests.desktop;

import com.epam.core.Configuration;
import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.desktop.DesktopHeaderLayout;
import com.epam.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePageHeaderVerificationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(HomePageHeaderVerificationTest.class);

    @DataProvider(name = "desktop headers")
    public static Object[][] headers() {
        String baseURL = Configuration.getBaseUrl();
        List<MenuItem> headers = new ArrayList<>();
        headers.add(new MenuItem("SERIES", baseURL + "/series"));
        headers.add(new MenuItem("MOVIES", baseURL + "/movies"));
        headers.add(new MenuItem("SPORTS", baseURL + "/sports"));
        headers.add(new MenuItem("DOCUMENTARIES", baseURL + "/documentaries"));
        headers.add(new MenuItem("COMEDY", baseURL + "/comedy"));
        return new Object[][]{{headers}};
    }

    @Test(dataProvider = "desktop headers")
    public void homePageHeaderVerificationTest(List<MenuItem> expectedHeaders) {
        HeaderLayout homePage = new DesktopHeaderLayout();
        List<MenuItem> actualHeaders = homePage.getPrimaryMenuItems();

        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size());

        for (int i = 0; i < actualHeaders.size(); i++) {
            MenuItem actualHeader = actualHeaders.get(i);
            MenuItem expectedHeader = expectedHeaders.get(i);
            LOGGER.debug("Actual: " + actualHeader + "\nExpected: " + expectedHeader);
            Assert.assertTrue(actualHeader.equals(expectedHeader));
        }
    }
}
