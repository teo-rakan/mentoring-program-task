package com.epam.tests.desktop;

import com.epam.core.Configuration;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.desktop.DesktopHeaderLayout;
import com.epam.tests.HeaderVerificationBaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.epam.pages.bean.RGBAColor.*;

public class HomePageHeaderVerificationTest extends HeaderVerificationBaseTest {

    private static final Logger LOGGER = LogManager.getLogger(HomePageHeaderVerificationTest.class);

    @DataProvider(name = "desktop headers")
    public static Object[][] headers() {
        String baseURL = Configuration.getBaseUrl();
        List<MenuItem> primary = Arrays.asList(
                new MenuItem("SERIES", baseURL + "/series", BLACK, WHITE),
                new MenuItem("MOVIES", baseURL + "/movies", BLACK, WHITE),
                new MenuItem("SPORTS", baseURL + "/sports", BLACK, WHITE),
                new MenuItem("DOCUMENTARIES", baseURL + "/documentaries", BLACK, WHITE),
                new MenuItem("COMEDY", baseURL + "/comedy", BLACK, WHITE));
        List<MenuItem> right = Arrays.asList(
                new MenuItem("GET SHOWTIME", baseURL + "/order", RED, WHITE),
                new MenuItem("SCHEDULES", null, BLACK, WHITE));
        return new Object[][]{{primary, right}};
    }

    @Test(dataProvider = "desktop headers")
    public void homePageHeaderVerificationTest(List<MenuItem> expectedPrimaryHeaders,
                                               List<MenuItem> expectedRightHeaders) {
        DesktopHeaderLayout headerLayout = new DesktopHeaderLayout();
        verifyMenu(headerLayout, expectedPrimaryHeaders, expectedRightHeaders);

    }



    private void verifyMenu(DesktopHeaderLayout header,
                            List<MenuItem> expectedPrimaryHeaders,
                            List<MenuItem> expectedRightHeaders) {
        verifyMenuItems(header.getPrimaryMenuItems(), expectedPrimaryHeaders);
        verifyMenuItems(header.getRightMenuItems(), expectedRightHeaders);
        Assert.assertTrue(header.isSearchIconDisplayed());
    }
}
