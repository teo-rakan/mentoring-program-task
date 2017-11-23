package com.epam.pages.mobile;

import com.epam.pages.BasePage;
import com.epam.pages.HeaderLayout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileHomePage extends BasePage {

    @FindBy(xpath = "//*[@data-label='menu close']")
    private WebElement menuButton;

    public MobileHomePage(WebDriver driver) {
        super(driver);
    }

    public HeaderLayout openMenu() {
        return new MobileHeaderLayout(driver);
    }
}
