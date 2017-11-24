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

    @FindBy(xpath = "//*[@class='global-navigation__primary-mobile-menu-item']")
    private List<WebElement> headerPrimaryMenuItems;

    @FindBy(xpath = "//*[@data-label='menu open']")
    private WebElement closeButton;

    public void close() {
        closeButton.click();
    }

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        LOGGER.debug("Get primary menu items as list [title, link]");
        WaitUtil.untilVisible(closeButton, driverManager.getDriver());
        return convertToMenuItemList(headerPrimaryMenuItems);
    }

    @Override
    public List<MenuItem> getRightMenuItems() {
        return null;
    }
}
