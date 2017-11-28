package com.epam.pages.desktop;

import com.epam.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterLayout extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(FooterLayout.class);

    @FindBy(className = "footer__logo")
    private WebElement logo;

    @FindBy(xpath = "//*[@class='footer-order-promo']//*[text()='Have Showtime?']")
    private WebElement haveShowtimeHeader;

    @FindBy(xpath = "//*[@class='footer-order-promo']//a[@data-label='stream']")
    private WebElement streamShowtimeLink;

    @FindBy(xpath = "//*[@class='footer-order-promo']//*[text()='Want Showtime? Subscribe.']")
    private WebElement wantShowtimeSubscribeHeader;

    @FindBy(xpath = "//*[@class='footer-order-promo']//a[@data-label='order']")
    private WebElement tryItNowForFreeLink;

    public boolean isLogoVisible() {
        LOGGER.debug("Check Showtime logo visibility");
        driverManager.waitUntilVisible(logo);
        return logo.isDisplayed();
    }

    public boolean isHaveShowtimeHeaderVisible() {
        LOGGER.debug("Check 'Have Showtime?' header visibility");
        driverManager.waitUntilVisible(haveShowtimeHeader);
        return haveShowtimeHeader.isDisplayed();
    }

    public boolean isWantShowtimeSubscribeHeaderVisible() {
        LOGGER.debug("Check 'Want Showtime? Subscribe.' header visibility");
        driverManager.waitUntilVisible(wantShowtimeSubscribeHeader);
        return wantShowtimeSubscribeHeader.isDisplayed();
    }

    public String getStreamShowtimeLink() {
        LOGGER.debug("Get 'Stream Showtime' link");
        driverManager.waitUntilVisible(streamShowtimeLink);
        return streamShowtimeLink.getAttribute("href");
    }

    public String getTryItNowForFreeLink() {
        LOGGER.debug("Get 'Try it for now for free' link");
        driverManager.waitUntilVisible(tryItNowForFreeLink);
        return tryItNowForFreeLink.getAttribute("href");
    }
}
