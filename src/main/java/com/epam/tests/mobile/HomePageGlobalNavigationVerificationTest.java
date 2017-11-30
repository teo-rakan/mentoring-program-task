package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.entity.MenuItem;
import com.epam.pages.mobile.MobileHeaderLayout;
import com.epam.tests.HeaderVerificationBaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static com.epam.utils.PropertyManager.get;
import static java.lang.String.format;

public class HomePageGlobalNavigationVerificationTest extends HeaderVerificationBaseTest {

    private final static String BLACK = get("color.black");
    private final static String WHITE = get("color.white");
    private final static String LIGHT_RED = get("color.red.light");
    private final static String PLACEHOLDER = "SEARCH";

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
                new MenuItem("TV SCHEDULE", baseURL + "/schedule", BLACK, LIGHT_RED),
                new MenuItem("ON DEMAND AND STREAMING SCHEDULE", baseURL + "/on-demand/series", BLACK, LIGHT_RED));

        MenuItem getShowtime = new MenuItem("GET SHOWTIME", baseURL + "/order", BLACK, WHITE);

        return new Object[][]{{primary, red, getShowtime}};
    }


    @Test(dataProvider = "mobile headers")
    public void verifyHomePageGlobalNavigation(List<MenuItem> primary,
                                               List<MenuItem> red,
                                               MenuItem getShowtime) {
        SoftAssert softAssert = new SoftAssert();
        String menuFailMessage = "Expanded menu has problems. See log for details";
        String placeholderFailMessage = "Search placeholder found: %s Expected: %s";
        String bgColorFailMessage = "Search input actual bg color: %s Expected: %s";
        MobileHeaderLayout headerLayout = new MobileHeaderLayout();

        softAssert.assertTrue(
                verifyExpandedMenu(headerLayout, primary, red, getShowtime),
                menuFailMessage);

        headerLayout.openSearchField();
        String bgColor = headerLayout.getSearchBackgroundColor();
        String placeholder = headerLayout.getSearchPlaceholder();

        softAssert.assertEquals(placeholder, PLACEHOLDER,
                format(placeholderFailMessage, placeholder, PLACEHOLDER));
        softAssert.assertEquals(bgColor, WHITE,
                format(bgColorFailMessage, bgColor, WHITE));
        softAssert.assertAll();
    }

    private boolean verifyExpandedMenu(MobileHeaderLayout headerLayout,
                                       List<MenuItem> primary,
                                       List<MenuItem> red,
                                       MenuItem getShowtime) {
        boolean match = true;
        headerLayout.openMenu();
        match &= verifyMenuItems(headerLayout.getPrimaryMenuItems(), primary);
        match &= verifyMenuItems(headerLayout.getRedMenuItems(), red);
        match &= verifyMenuItem(headerLayout.getGetShowtime(), getShowtime);
        headerLayout.closeMenu();
        return match;
    }
}
