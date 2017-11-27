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

    protected List<MenuItem> convertToMenuItemList(List<WebElement> menuItems) {
        List<MenuItem> entityList = new ArrayList<>();

        for (WebElement menuItem : menuItems) {
            LOGGER.debug(menuItem);
            entityList.add(convertToMenuItem(menuItem));
        }
        return entityList;
    }

    protected MenuItem convertToMenuItem(WebElement menuItem) {
        return new MenuItem(menuItem.getText(),
                menuItem.getAttribute("href"),
                menuItem.getCssValue("background-color"),
                menuItem.getCssValue("color"));
    }
}
