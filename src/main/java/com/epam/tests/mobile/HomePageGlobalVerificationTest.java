package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.mobile.MobileHeaderLayout;
import com.epam.pages.mobile.MobileHomePage;
import com.epam.tests.BaseTest;
import com.epam.utils.HttpUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePageGlobalVerificationTest extends BaseTest {

    @DataProvider(name = "mobile headers")
    public static Object[][] headers() {
        String baseURL = Configuration.getBaseUrl();

        List<MenuItem> primary = new ArrayList<>();
        primary.add(new MenuItem("HOME", baseURL + "/"));
        primary.add(new MenuItem("SERIES", baseURL + "/series"));
        primary.add(new MenuItem("MOVIES", baseURL + "/movies"));
        primary.add(new MenuItem("SPORTS", baseURL + "/sports"));
        primary.add(new MenuItem("DOCUMENTARIES", baseURL + "/documentaries"));
        primary.add(new MenuItem("COMEDY", baseURL + "/comedy"));

        List<MenuItem> red = new ArrayList<>();
        red.add(new MenuItem("TV SCHEDULE", baseURL + "/schedule"));
        red.add(new MenuItem("ON DEMAND AND STREAMING SCHEDULE", baseURL + "/on-demand/series"));

        MenuItem getShowtime = new MenuItem("GET SHOWTIME", baseURL + "/order");

        return new Object[][]{{primary, red, getShowtime}};
    }


    @Test(dataProvider = "mobile headers")
    public void homePageGlobalVerificationTest(List<MenuItem> primary,
                                               List<MenuItem> red,
                                               MenuItem getShowtime) {
        MobileHeaderLayout headerLayout = new MobileHomePage().openMenu();
        verifyMenuItems(headerLayout.getPrimaryMenuItems(), primary);
        verifyMenuItems(headerLayout.getRedMenuItems(), red);
        verifyMenuItem(headerLayout.getGetShowtime(), getShowtime);
        headerLayout.closeMenu();

    }

    private void verifyMenuItems(List<MenuItem> actualHeaders, List<MenuItem> expectedHeaders) {
        Assert.assertEquals(actualHeaders.size(), expectedHeaders.size(),
                "Expected header count: " + expectedHeaders.size()
                        + " Found: " + actualHeaders.size());

        for (int i = 0; i < actualHeaders.size(); i++) {
            MenuItem actualHeader = actualHeaders.get(i);
            MenuItem expectedHeader = expectedHeaders.get(i);
            verifyMenuItem(actualHeader, expectedHeader);
        }
    }

    private void verifyMenuItem(MenuItem actual, MenuItem expected) {
        String link = actual.getLink();
        int responseCode = HttpUtil.getStatusCode(link);

        Assert.assertTrue(actual.equals(expected),
                "\nActual: " + actual + "\nExpected: " + expected);
        Assert.assertEquals(responseCode, 200,
                actual.getTitle() + " link is unavailable: " + link);
    }
}
