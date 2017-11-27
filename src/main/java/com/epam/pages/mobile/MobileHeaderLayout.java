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

    @FindBy(xpath = "//*[@class='global-navigation__primary-mobile-menu-item']//a")
    private List<WebElement> primaryMenuItems;

    @FindBy(xpath = "//*[contains(@class,'global-navigation--red')]//a")
    private List<WebElement> redMenuItems;

    @FindBy(xpath = "//*[@class='global-navigation__get-showtime-mobile-menu-item']//a")
    private WebElement showTimeMenuItem;

    @FindBy(xpath = "//*[@data-label='menu open']")
    private WebElement closeButton;

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        LOGGER.debug("Get primary menu items as list [title, link]");
        WaitUtil.untilVisible(closeButton, driverManager.getDriver());
        return convertToMenuItemList(primaryMenuItems);
    }

    public List<MenuItem> getRedMenuItems() {
        LOGGER.debug("Get red menu items as list [title, link]");
        WaitUtil.untilVisible(closeButton, driverManager.getDriver());
        return convertToMenuItemList(redMenuItems);
    }

    public MenuItem getGetShowtime() {
        LOGGER.debug("Get 'Get showtime' menu item");
        WaitUtil.untilVisible(closeButton, driverManager.getDriver());
        return convertToMenuItem(showTimeMenuItem);
    }

    public MobileHomePage closeMenu() {
        LOGGER.debug("Close menu layout");
        WaitUtil.untilVisible(closeButton, driverManager.getDriver());
        closeButton.click();
        return new MobileHomePage();
    }
}
