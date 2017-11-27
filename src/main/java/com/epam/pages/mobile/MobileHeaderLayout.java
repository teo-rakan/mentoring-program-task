package com.epam.pages.mobile;

import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import com.epam.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MobileHeaderLayout extends HeaderLayout {

    private static final Logger LOGGER = LogManager.getLogger(MobileHeaderLayout.class);

    @FindBy(xpath = "//div[@data-label='menu open']")
    private WebElement hamburgerIcon;

    @FindBy(xpath = "//*[@class='global-navigation__primary-mobile-menu-item']//a")
    private List<WebElement> primaryMenuItems;

    @FindBy(xpath = "//*[contains(@class,'global-navigation--red')]//a")
    private List<WebElement> redMenuItems;

    @FindBy(xpath = "//*[@class='global-navigation__get-showtime-mobile-menu-item']//a")
    private WebElement showTimeMenuItem;

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        LOGGER.debug("Get primary menu items as list [title, link]");
        driverManager.waitUntilVisible(hamburgerIcon);
        return convertToMenuItemList(primaryMenuItems);
    }

    public List<MenuItem> getRedMenuItems() {
        LOGGER.debug("Get red menu items as list [title, link]");
        driverManager.waitUntilVisible(hamburgerIcon);
        return convertToMenuItemList(redMenuItems);
    }

    public MenuItem getGetShowtime() {
        LOGGER.debug("Get 'Get showtime' menu item");
        driverManager.waitUntilVisible(hamburgerIcon);
        return convertToMenuItem(showTimeMenuItem);
    }

    public MobileHeaderLayout openMenu() {
        LOGGER.debug("Open header menu");
        driverManager.waitUntilVisible(hamburgerIcon);
        hamburgerIcon.click();
        return new MobileHeaderLayout();
    }

    public MobileHeaderLayout closeMenu() {
        LOGGER.debug("Close menu layout");
        driverManager.waitUntilVisible(hamburgerIcon);
        hamburgerIcon.click();
        return this;
    }
}
