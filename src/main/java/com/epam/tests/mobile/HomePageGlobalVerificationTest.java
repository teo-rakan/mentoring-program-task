package com.epam.tests.mobile;

import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import com.epam.pages.mobile.MobileHomePage;
import com.epam.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageGlobalVerificationTest extends BaseTest {

    @Test
    public void homePageGlobalVerificationTest() {
        HeaderLayout homePage = new MobileHomePage().openMenu();
        List<MenuItem> menuItems = homePage.getPrimaryMenuItems();

        Assert.assertEquals(menuItems.size(), 6);
    }
}
