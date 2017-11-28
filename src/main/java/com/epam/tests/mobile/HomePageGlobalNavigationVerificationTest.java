package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.entity.MenuItem;
import com.epam.pages.mobile.MobileHeaderLayout;
import com.epam.tests.HeaderVerificationBaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.epam.pages.entity.RGBAColor.*;

public class HomePageGlobalNavigationVerificationTest extends HeaderVerificationBaseTest {

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
}
