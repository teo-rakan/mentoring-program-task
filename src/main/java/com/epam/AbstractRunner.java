package com.epam;

import com.epam.utils.PropertyManager;


abstract class AbstractRunner {

    static void setSystemProperties() {
        System.setProperty("browser.name", PropertyManager.get("browser.name"));
    }
}
