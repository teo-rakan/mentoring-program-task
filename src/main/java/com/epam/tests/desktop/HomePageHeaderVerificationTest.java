package com.epam.tests.desktop;

import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.desktop.DesktopHeaderLayout;
import com.epam.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageHeaderVerificationTest extends BaseTest {

    @Test
    public void homePageHeaderVerificationTest() {
        HeaderLayout homePage = new DesktopHeaderLayout(driver);
        List<MenuItem> primaryMenuItems = homePage.getPrimaryMenuItems();

        Assert.assertEquals(primaryMenuItems.size(), 5);
    }
}
