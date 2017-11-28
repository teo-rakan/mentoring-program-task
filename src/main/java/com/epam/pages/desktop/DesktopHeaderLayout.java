package com.epam.pages.desktop;

import com.epam.pages.HeaderLayout;
import com.epam.pages.entity.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DesktopHeaderLayout extends HeaderLayout {

    private static final Logger LOGGER = LogManager.getLogger(DesktopHeaderLayout.class);

    @FindBy(xpath = "//nav[@data-context='global navigation']")
    private WebElement header;

    @FindBy(xpath = "//*[@class='global-navigation__primary-menu-item']//a")
    private List<WebElement> headerPrimaryMenuItems;

    @FindBy(xpath = "//*[@class='global-navigation__right-menu-item']//a")
    private List<WebElement> headerRightMenuItems;

    private List<MenuItem> convertIfVisible(List<WebElement> menuItems) {
        driverManager.waitUntilVisible(header);
        return (menuItems.stream().allMatch(WebElement::isDisplayed))
                ? convertToMenuItemList(menuItems)
                : null;
    }

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        LOGGER.debug("Get primary menu items as list [title, link]");
        return convertIfVisible(headerPrimaryMenuItems);
    }

    public List<MenuItem> getRightMenuItems() {
        LOGGER.debug("Get right menu items as list [title, link]");
        return convertIfVisible(headerRightMenuItems);
    }
}
