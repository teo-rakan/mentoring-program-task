package com.epam.core;

public class Configuration {
    private static String appiumURL;
    private static String browserName;
    private static String targetPlatform;
    private static String baseUrl;
    private static String[] suites;

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

    public static String[] getSuites() {
        return suites;
    }

    public static void setSuites(String[] suites) {
        Configuration.suites = suites;
    }

    public static String getTargetPlatform() {
        return targetPlatform;
    }

    public static void setTargetPlatform(String targetPlatform) {
        Configuration.targetPlatform = targetPlatform;
    }

    public static boolean isMobile() {
        return targetPlatform.toLowerCase().matches("(ios|android)");
    }
}
