package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.mobile.MobileHeaderLayout;
import com.epam.pages.mobile.MobileHomePage;
import com.epam.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePageGlobalVerificationTest extends BaseTest {

    @DataProvider(name = "mobile headers")
    public static Object[][] headers() {
        String baseURL = Configuration.getBaseUrl();
        List<MenuItem> headers = new ArrayList<>();
        headers.add(new MenuItem("HOME", baseURL + "/"));
        headers.add(new MenuItem("SERIES", baseURL + "/series"));
        headers.add(new MenuItem("MOVIES", baseURL + "/movies"));
        headers.add(new MenuItem("SPORTS", baseURL + "/sports"));
        headers.add(new MenuItem("DOCUMENTARIES", baseURL + "/documentaries"));
        headers.add(new MenuItem("COMEDY", baseURL + "/comedy"));
        return new Object[][]{{headers}};
    }

    @Test(dataProvider = "mobile headers")
    public void homePageGlobalVerificationTest(List<MenuItem> expectedHeaders) {
        HeaderLayout homePage = new MobileHomePage().openMenu();
        List<MenuItem> actualHeaders = homePage.getPrimaryMenuItems();

        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size(),
                "Expected header count: " + expectedHeaders.size()
                        + " Found: " + actualHeaders.size());

        for (int i = 0; i < actualHeaders.size(); i++) {
            MenuItem actualHeader = actualHeaders.get(i);
            MenuItem expectedHeader = expectedHeaders.get(i);
            Assert.assertTrue(actualHeader.equals(expectedHeader),
                    "Actual: " + actualHeader + "\nExpected: " + expectedHeader);
        }
    }
}
