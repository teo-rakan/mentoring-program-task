package com.epam.tests.mobile;

import com.epam.core.Configuration;
import com.epam.pages.entity.Link;
import com.epam.pages.mobile.OrderPage;
import com.epam.tests.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class OrderTrayVerificationTest extends BaseTest {

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
        String failMessage = "Actual title: %s Expected: %s";
        driverManager.open(Configuration.getBaseUrl() + "/order");
        OrderPage page = new OrderPage();
        List<Link> images = page.getAllProviderLinks();
        String expectedTitle = page.getTitle().toUpperCase();

        assertEquals(title, expectedTitle, format(failMessage, title, expectedTitle));
        verifyProviders(images, expectedLogos);
    }

    private void verifyProviders(List<Link> images, String[] expectedLogos) {
        String failMessage = "Actual image title: %s Expected: %s";
        for (int i = 0; i < images.size(); i++) {
            String imageTitle = images.get(i).getTitle().toLowerCase();
            String imageLink = images.get(i).getLink().toLowerCase();
            String expectedTitle = expectedLogos[i].toLowerCase();

            assertEquals(imageTitle, expectedTitle,
                    format(failMessage, imageTitle, expectedTitle));
            if (i > 0) verifyLinkContainsProviderName(imageTitle, imageLink);
            verifyLink(imageTitle, imageLink);
        }
    }

    private void verifyLinkContainsProviderName(String title, String link) {
        String failMessage = "Cannot find provider part name '%s' in the link: %s";
        String firstTitleWord = title.contains(" ") ? title.substring(0, title.indexOf(" ")) : title;
        String regex = "/" + firstTitleWord + "(-\\w*)*\\.\\w*$";
        Pattern orderReferencePattern = Pattern.compile(regex);
        Matcher matcher = orderReferencePattern.matcher(link);

        assertTrue(matcher.find(), format(failMessage, firstTitleWord, link));
    }

}
