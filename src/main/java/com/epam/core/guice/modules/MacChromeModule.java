package com.epam.core.guice.modules;

public class MacChromeModule extends WindowsChromeModule {

    public MacChromeModule() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    }
}
