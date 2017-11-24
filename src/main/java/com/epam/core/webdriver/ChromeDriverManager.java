package com.epam.core.webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class ChromeDriverManager extends DriverManager {

    @Override
    void createDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
