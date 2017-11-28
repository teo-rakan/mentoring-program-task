package com.epam.pages.desktop;

import com.epam.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterLayout extends BasePage {

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
        driverManager.waitUntilVisible(logo);
        return logo.isDisplayed();
    }

    public boolean isHaveShowtimeHeaderVisible() {
        driverManager.waitUntilVisible(haveShowtimeHeader);
        return haveShowtimeHeader.isDisplayed();
    }

    public boolean isWantShowtimeSubscribeHeaderVisible() {
        driverManager.waitUntilVisible(wantShowtimeSubscribeHeader);
        return wantShowtimeSubscribeHeader.isDisplayed();
    }

    public String getStreamShowtimeLink() {
        driverManager.waitUntilVisible(streamShowtimeLink);
        return streamShowtimeLink.getAttribute("href");
    }

    public String getTryItNowForFreeLink() {
        driverManager.waitUntilVisible(tryItNowForFreeLink);
        return tryItNowForFreeLink.getAttribute("href");
    }
}
