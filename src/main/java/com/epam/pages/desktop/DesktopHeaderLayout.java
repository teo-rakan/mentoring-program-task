package com.epam.pages.desktop;

import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import com.epam.utils.WaitUtil;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DesktopHeaderLayout extends HeaderLayout {

    @FindBy(xpath = "//nav[@data-context='global navigation']")
    private WebElement header;

    @FindBy(className = "global-navigation__primary-menu-item")
    private List<WebElement> headerPrimaryMenuItems;

    @FindBy(className = "global-navigation__right-menu-item")
    private List<WebElement> headerRightMenuItems;

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        //todo check visibility
        return convertToMenuItemList(headerPrimaryMenuItems);
    }

    @Override
    public List<MenuItem> getRightMenuItems() {
        return convertToMenuItemList(headerRightMenuItems);
    }

    public boolean isHeaderVisible() {
        try {
            WaitUtil.untilVisible(header, driverManager.getDriver());
        } catch (WebDriverException exception) {
            return false;
        }
        return true;
    }

}
