package com.epam.pages.desktop;

import com.epam.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScheduleDrawerLayer extends BasePage {

    @FindBy(className = "schedule-drawer--header")
    private WebElement header;

    @FindBy(className = "schedule-drawer__icon-close")
    private WebElement closeIcon;

    @FindBy(xpath = "//div[@class='schedule-drawer__inner']//a[@href='/schedule']")
    private WebElement seeFullTVScheduleLink;

    public boolean isCloseIconVisible() {
        driverManager.waitUntilVisible(closeIcon);
        return closeIcon.isDisplayed();
    }


}
