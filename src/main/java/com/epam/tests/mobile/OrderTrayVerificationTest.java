package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.entity.Link;
import com.epam.pages.mobile.OrderPage;
import com.epam.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;


public class OrderTrayVerificationTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(OrderTrayVerificationTest.class);

    @DataProvider(name = "providers")
    public static Object[][] headers() {
        String title = "HOW DO YOU WANT TO GET SHOWTIME?";

        String[] logos = {"From Showtime", "Amazon Prime",
                "Amazon Devices", "Android", "Apple", "DIRECTV NOW",
                "Hulu", "PlayStation Vue", "Roku", "Sling", "Smart TVs",
                "Xbox One", "YouTube TV", "TV Provider"};

        return new Object[][]{{title, logos}};
    }

    @Test(dataProvider = "providers")
    public void orderVerificationTest(String title, String[] expectedLogos) {
        SoftAssert softAssert = new SoftAssert();
        String titleFailMessage = "Actual title: %s Expected: %s";
        String providersFailMessage = "Provider list has problems. See log.";
        driverManager.open(Configuration.getBaseUrl() + "/order");
        OrderPage page = new OrderPage();
        List<Link> images = page.getAllProviderLinks();
        String expectedTitle = page.getTitle().toUpperCase();

        softAssert.assertEquals(title, expectedTitle,
                format(titleFailMessage, title, expectedTitle));
        softAssert.assertTrue(verifyProviders(images, expectedLogos),
                providersFailMessage);
        softAssert.assertAll();
    }

    private boolean verifyProviders(List<Link> images, String[] expectedLogos) {
        String diffCountMessage = "Actual image count: %d Expected: %d";
        String failMessage = "Actual image title: %s Expected: %s";
        int actualCount = images.size();
        int expectedCount = expectedLogos.length;
        boolean match = true;

        // Check count
        if (actualCount != expectedCount) {
            LOGGER.error(format(diffCountMessage, actualCount, expectedCount));
            LOGGER.info("Expected:");
            Arrays.stream(expectedLogos).forEach(LOGGER::info);
            LOGGER.info("Found:");
            images.stream().map(i -> i.getTitle()).forEach(LOGGER::info);
            return false;
        }

        for (int i = 0; i < images.size(); i++) {
            String imageTitle = images.get(i).getTitle().toLowerCase();
            String imageLink = images.get(i).getLink().toLowerCase();
            String expectedTitle = expectedLogos[i].toLowerCase();

            if (!imageTitle.equals(expectedTitle)) {
                match = false;
                LOGGER.error(format(failMessage, imageTitle, expectedTitle));
            } else {
                if (i > 0)
                    match &= isLinkContainsProviderName(imageTitle, imageLink);
                match &= verifyLink(imageTitle, imageLink);
            }
        }
        return match;
    }

    private boolean isLinkContainsProviderName(String title, String link) {
        String failMessage = "Cannot find provider part name '%s' in the link: %s";
        String firstTitleWord = title.contains(" ") ? title.substring(0, title.indexOf(" ")) : title;
        String regex = "/" + firstTitleWord + "(-\\w*)*\\.\\w*$";
        Pattern orderReferencePattern = Pattern.compile(regex);
        Matcher matcher = orderReferencePattern.matcher(link);

        if (!matcher.find()) {
            LOGGER.error(format(failMessage, firstTitleWord, link));
            return false;
        }
        return true;
    }

}
