package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.mobile.MobileHeaderLayout;
import com.epam.tests.BaseTest;
import com.epam.utils.HttpUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.epam.pages.bean.RGBAColor.*;

public class HomePageGlobalVerificationTest extends BaseTest {

    @DataProvider(name = "mobile headers")
    public static Object[][] headers() {
        String baseURL = Configuration.getBaseUrl();

        List<MenuItem> primary = Arrays.asList(
                new MenuItem("HOME", baseURL + "/", BLACK, WHITE),
                new MenuItem("SERIES", baseURL + "/series", BLACK, WHITE),
                new MenuItem("MOVIES", baseURL + "/movies", BLACK, WHITE),
                new MenuItem("SPORTS", baseURL + "/sports", BLACK, WHITE),
                new MenuItem("DOCUMENTARIES", baseURL + "/documentaries", BLACK, WHITE),
                new MenuItem("COMEDY", baseURL + "/comedy", BLACK, WHITE));

        List<MenuItem> red = Arrays.asList(
                new MenuItem("TV SCHEDULE", baseURL + "/schedule", BLACK, RED),
                new MenuItem("ON DEMAND AND STREAMING SCHEDULE", baseURL + "/on-demand/series", BLACK, RED));

        MenuItem getShowtime = new MenuItem("GET SHOWTIME", baseURL + "/order", BLACK, WHITE);

        return new Object[][]{{primary, red, getShowtime}};
    }


    @Test(dataProvider = "mobile headers")
    public void homePageGlobalVerificationTest(List<MenuItem> primary,
                                               List<MenuItem> red,
                                               MenuItem getShowtime) {
        MobileHeaderLayout headerLayout = new MobileHeaderLayout();
        verifyExpandedMenu(headerLayout, primary, red, getShowtime);

        headerLayout.openSearchField();
        String bgColor = headerLayout.getSearchBackgroundColor();
        String placeholder = headerLayout.getSearchPlaceholder();

        Assert.assertEquals(placeholder, "SEARCH");
        Assert.assertEquals(bgColor, WHITE.toString());
    }

    private void verifyExpandedMenu(MobileHeaderLayout headerLayout,
                                    List<MenuItem> primary,
                                    List<MenuItem> red,
                                    MenuItem getShowtime) {
        headerLayout.openMenu();
        verifyMenuItems(headerLayout.getPrimaryMenuItems(), primary);
        verifyMenuItems(headerLayout.getRedMenuItems(), red);
        verifyMenuItem(headerLayout.getGetShowtime(), getShowtime);
        headerLayout.closeMenu();
    }

    private void verifyMenuItems(List<MenuItem> actualHeaders,
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

    private void verifyMenuItem(MenuItem actual, MenuItem expected) {
        String link = actual.getLink();
        int responseCode = HttpUtil.getStatusCode(link);

        Assert.assertTrue(actual.equals(expected),
                "\nActual: " + actual + "\nExpected: " + expected);
        Assert.assertEquals(responseCode, 200,
                actual.getTitle() + " link is unavailable: " + link);
    }
}
