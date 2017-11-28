package com.epam.pages.mobile;

import com.epam.pages.BasePage;
import com.epam.pages.bean.Link;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(OrderPage.class);

    @FindBy(className = "order-tray__body-headline")
    private WebElement title;

    @FindBy(className = "order-card__logo")
    private List<WebElement> providerList;

    public String getTitle() {
        driverManager.waitUntilVisible(title);
        return title.getText();
    }

    public List<Link> getAllProviderLinks() {
        LOGGER.debug("Get providers as list [alt name, src link]");
        driverManager.waitUntilVisible(title);
        return convertToLinks(providerList);
    }

    private List<Link> convertToLinks(List<WebElement> images) {
        List<Link> entityList = new ArrayList<>();

        for (WebElement image : images) {
            LOGGER.debug(image);
            entityList.add(convertToLink(image));
        }
        return entityList;
    }

    private Link convertToLink(WebElement image) {
        return new Link(image.getAttribute("alt"),
                image.getAttribute("src"));
    }
}
