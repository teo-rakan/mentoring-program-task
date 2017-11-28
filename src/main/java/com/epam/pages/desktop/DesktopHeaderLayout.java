package com.epam.pages.desktop;

import com.epam.pages.HeaderLayout;
import com.epam.pages.entity.MenuItem;
import org.openqa.selenium.WebDriverException;
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

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        //todo check visibility
        return convertToMenuItemList(headerPrimaryMenuItems);
    }

    public List<MenuItem> getRightMenuItems() {
        return convertToMenuItemList(headerRightMenuItems);
    }

    public boolean isHeaderVisible() {
        try {
            driverManager.waitUntilVisible(header);
        } catch (WebDriverException exception) {
            return false;
        }
        return true;
    }

}
