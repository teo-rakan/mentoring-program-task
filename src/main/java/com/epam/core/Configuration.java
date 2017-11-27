package com.epam.core;

public class Configuration {
    private static String appiumURL;
    private static String browserName;
    private static String baseUrl;

    public static String getAppiumURL() {
        return appiumURL;
    }

    public static void setAppiumURL(String appiumURL) {
        Configuration.appiumURL = appiumURL;
    }

    public static String getBrowserName() {
        return browserName;
    }

    public static void setBrowserName(String browserName) {
        Configuration.browserName = browserName;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        Configuration.baseUrl = baseUrl;
    }
}