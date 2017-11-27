package com.epam.pages;

import com.epam.pages.bean.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public abstract class HeaderLayout extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HeaderLayout.class);

    @FindBy(className = "global-navigation__logo")
    private WebElement showtimeLogo;

    @FindBy(className = "global-navigation__search-icon")
    private WebElement searchButton;

    public abstract List<MenuItem> getPrimaryMenuItems();

    public abstract List<MenuItem> getRightMenuItems();

    protected List<MenuItem> convertToMenuItemList(List<WebElement> headerMenuItems) {
        List<MenuItem> menuItems = new ArrayList<>();

        for (WebElement headerMenuItem : headerMenuItems) {
            LOGGER.debug(headerMenuItem);
            menuItems.add(new MenuItem(headerMenuItem.getText(), headerMenuItem.getAttribute("href")));
        }
        return menuItems;
    }
}
