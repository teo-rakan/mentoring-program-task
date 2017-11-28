package com.epam.core.cli;

import com.epam.core.Configuration;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliOptions {

    private static final Logger LOGGER = LogManager.getLogger(CliOptions.class);

    private static final String BASE_URL_OPTION = "base_url";
    private static final String BASE_URL_DEFAULT = "http://www.sho.com";
    private static final String BROWSER_NAME_OPTION = "browser_name";
    private static final String BROWSER_NAME_DEFAULT = "Chrome";
    private static final String TARGET_PLATFORM_OPTION = "target_platform";
    private static final String TARGET_PLATFORM_DEFAULT = "Windows";
    private static final String SUITE_OPTION = "suite";
    private static final String SUITE_DIR_DEFAULT = "./suite/";
    private static final String[] SUITE_DEFAULT = {SUITE_DIR_DEFAULT + "desktop-smoke.xml"};

    private static Option getBaseUrlOption() {
        return new Option("bu", BASE_URL_OPTION, true,
                "Base URL value. Default: " + BASE_URL_DEFAULT);
    }

    private static Option getBrowserOption() {
        return new Option("bn", BROWSER_NAME_OPTION, true,
                "Browser name. For mobile: ios, android. For Web: "
                        + "safari, ie, chrome. Default: " + BROWSER_NAME_DEFAULT);
    }

    private static Option getPlatformOption() {
        return new Option("tp", TARGET_PLATFORM_OPTION, true,
                "Target platform. For mobile: ios, android. For desktop: "
                        + "windows, macos. Default: " + TARGET_PLATFORM_DEFAULT);
    }

    private static Option getSuiteOption() {
        return new Option("s", SUITE_OPTION, true,
                "TestNG suite file(-es) path. Default: "
                        + Arrays.toString(SUITE_DEFAULT));
    }

    private static Options getAllOptions() {
        Options options = new Options();
        options.addOption(getBaseUrlOption());
        options.addOption(getBrowserOption());
        options.addOption(getSuiteOption());
        options.addOption(getPlatformOption());
        return options;
    }

    public static void parseCmdLineArgs(String[] args) {
        try {
            CommandLine cl = new GnuParser().parse(getAllOptions(), args);
            String baseUrl = cl.getOptionValue(BASE_URL_OPTION, BASE_URL_DEFAULT);
            String browser = cl.getOptionValue(BROWSER_NAME_OPTION, BROWSER_NAME_DEFAULT);
            String target = cl.getOptionValue(TARGET_PLATFORM_OPTION, TARGET_PLATFORM_DEFAULT);
            String[] suites = cl.getOptionValues(SUITE_OPTION);

            Configuration.setBaseUrl(baseUrl);
            Configuration.setBrowserName(browser);
            Configuration.setTargetPlatform(target);
            setSuites(suites);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void setSuites(String[] xmlSuites) {
        if (xmlSuites != null && xmlSuites.length > 0) {
            List<String> validSuites = validateSuitePaths(xmlSuites);
            if (validSuites.isEmpty()) {
                LOGGER.warn("Default suite(-es) will be used: "
                        + Arrays.toString(SUITE_DEFAULT));
                Configuration.setSuites(SUITE_DEFAULT);
            } else {
                Configuration.setSuites(validSuites.toArray(new String[0]));
            }
        } else {
            Configuration.setSuites(SUITE_DEFAULT);
        }
    }

    private static List<String> validateSuitePaths(String[] xmlSuites) {
        List<String> validSuites = new ArrayList<>();
        for (String xmlSuite : xmlSuites) {
            if (isFileExists(xmlSuite))
                validSuites.add(xmlSuite);
            else if (isFileExists("." + xmlSuite))
                validSuites.add("." + xmlSuite);
            else if (isFileExists(SUITE_DIR_DEFAULT + xmlSuite))
                validSuites.add(SUITE_DIR_DEFAULT + xmlSuite);
            else
                LOGGER.error("Cannot find suite file: " + xmlSuite);
        }
        return validSuites;
    }

    private static boolean isFileExists(String relativeOrFullPath) {
        return new File(relativeOrFullPath).exists();
    }
}

