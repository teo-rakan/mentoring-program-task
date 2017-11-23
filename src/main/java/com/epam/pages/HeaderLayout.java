package com.epam.pages;

import com.epam.pages.bean.MenuItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public abstract class HeaderLayout extends BasePage {

    @FindBy(className = "global-navigation__logo")
    private WebElement showtimeLogo;

    @FindBy(className = "global-navigation__search-icon")
    private WebElement searchButton;

    protected HeaderLayout(WebDriver driver) {
        super(driver);
    }

    public abstract List<MenuItem> getPrimaryMenuItems();
    public abstract List<MenuItem> getRightMenuItems();

    protected List<MenuItem> convertToMenuItemList(List<WebElement> headerMenuItems) {
        List<MenuItem> menuItems = new ArrayList<>();

        for (WebElement headerMenuItem : headerMenuItems) {
            menuItems.add(new MenuItem(headerMenuItem.getText(), headerMenuItem.getAttribute("href")));
        }
        return menuItems;
    }
}
