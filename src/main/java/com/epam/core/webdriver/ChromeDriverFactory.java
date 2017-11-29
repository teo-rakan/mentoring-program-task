package com.epam.core.webdriver;

import com.epam.core.guice.GuiceInjector;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;


public class ChromeDriverFactory extends DriverFactory {

    @Inject
    private String chromeDriverPath;

    @Override
    WebDriver createDriver() {
        GuiceInjector.get().injectMembers(this);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1850, 995));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
