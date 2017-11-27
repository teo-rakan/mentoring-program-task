package com.epam.pages.mobile;

import com.epam.pages.BasePage;
import com.epam.pages.HeaderLayout;
import com.epam.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileHomePage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MobileHomePage.class);

    @FindBy(xpath = "//div[@data-label='menu open']")
    private WebElement menuButton;

    public MobileHomePage() {
        LOGGER.debug("Create home page object");
    }

    public MobileHeaderLayout openMenu() {
        LOGGER.debug("Open header menu");
        WaitUtil.untilVisible(menuButton, driverManager.getDriver());
        menuButton.click();
        return new MobileHeaderLayout();
    }
}
