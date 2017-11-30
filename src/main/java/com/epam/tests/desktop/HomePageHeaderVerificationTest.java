package com.epam.tests.desktop;

import com.epam.core.Configuration;
import com.epam.pages.desktop.DesktopHeaderLayout;
import com.epam.pages.entity.MenuItem;
import com.epam.tests.HeaderVerificationBaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static com.epam.utils.PropertyManager.get;
import static java.lang.String.format;

public class HomePageHeaderVerificationTest extends HeaderVerificationBaseTest {

    private final static String BLACK = get("color.black");
    private final static String WHITE = get("color.white");
    private final static String RED = get("color.red");

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
    public void verifyHomePageHeader(List<MenuItem> expectedPrimaryHeaders,
                                     List<MenuItem> expectedRightHeaders) {
        SoftAssert softAssert = new SoftAssert();
        DesktopHeaderLayout header = new DesktopHeaderLayout();
        List<MenuItem> primaryMenuItems = header.getPrimaryMenuItems();
        List<MenuItem> rightMenuItems = header.getRightMenuItems();
        String menuFailMessage = "%s menu items have problems. See log for details";
        String searchIconFailMessage = "Search icon isn't displayed";

        softAssert.assertTrue(
                verifyMenuItems(primaryMenuItems, expectedPrimaryHeaders),
                format(menuFailMessage, "Primary"));
        softAssert.assertTrue(
                verifyMenuItems(rightMenuItems, expectedRightHeaders),
                format(menuFailMessage, "Right"));
        softAssert.assertTrue(header.isSearchIconDisplayed(), searchIconFailMessage);
        softAssert.assertAll();
    }
}
