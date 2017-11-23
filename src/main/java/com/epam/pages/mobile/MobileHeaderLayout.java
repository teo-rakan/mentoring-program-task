package com.epam.pages.mobile;

import com.epam.pages.HeaderLayout;
import com.epam.pages.bean.MenuItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MobileHeaderLayout extends HeaderLayout {

    @FindBy(xpath = "//*[@class='global-navigation__primary-mobile-menu-item']")
    private List<WebElement> headerPrimaryMenuItems;

    @FindBy(xpath = "//*[@data-label='menu open']")
    private WebElement closeButton;

    protected MobileHeaderLayout(WebDriver driver) {
        super(driver);
    }

    public void close() {
        closeButton.click();
    }

    @Override
    public List<MenuItem> getPrimaryMenuItems() {
        return convertToMenuItemList(headerPrimaryMenuItems);
    }

    @Override
    public List<MenuItem> getRightMenuItems() {
        return null;
    }
}
