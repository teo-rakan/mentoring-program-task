package com.epam.pages.desktop;

import com.epam.pages.HeaderLayout;
import com.epam.pages.entity.MenuItem;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DesktopHeaderLayout extends HeaderLayout {

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
        return convertIfVisible(headerPrimaryMenuItems);
    }

    public List<MenuItem> getRightMenuItems() {
        return convertIfVisible(headerRightMenuItems);
    }
}
