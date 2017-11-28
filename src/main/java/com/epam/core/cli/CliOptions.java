package com.epam.core.cli;

import com.epam.core.Configuration;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CliOptions {

    private static final Logger LOGGER = LogManager.getLogger(CliOptions.class);

    private static final String BASE_URL_OPTION = "base_url";
    private static final String BASE_URL_DEFAULT = "http://www.sho.com";
    private static final String BROWSER_NAME_OPTION = "browser_name";
    private static final String BROWSER_NAME_DEFAULT = "Chrome";
    private static final String SUITE_OPTION = "suite";
    private static final String[] SUITE_DEFAULT = {"testng.xml"};

    private static Option getBaseUrlOption() {
        return new Option("bu", BASE_URL_OPTION, true,
                "Set base URL value. Default: " + BASE_URL_DEFAULT);
    }

    private static Option getBrowserOption() {
        return new Option("bn", BROWSER_NAME_OPTION, true,
                "Set browser name for Mobile: iOS, Android. For Web: Safari, IE, Chrome.");
    }

    private static Option getSuiteOption() {
        return new Option("s", SUITE_OPTION, true,
                "Set testNG suite file(-es) path");
    }

    private static Options getAllOptions() {
        Options options = new Options();
        options.addOption(getBaseUrlOption());
        options.addOption(getBrowserOption());
        options.addOption(getSuiteOption());
        return options;
    }

    public static void parseCmdLineArgs(String[] args) {
        try {
            CommandLine cl = new GnuParser().parse(getAllOptions(), args);
            String baseUrl = cl.getOptionValue(BASE_URL_OPTION, BASE_URL_DEFAULT);
            String browser = cl.getOptionValue(BROWSER_NAME_OPTION, BROWSER_NAME_DEFAULT);
            String[] suites = cl.getOptionValues(SUITE_OPTION);

            Configuration.setBaseUrl(baseUrl);
            Configuration.setBrowserName(browser);
            Configuration.setSuites((suites == null || suites.length == 0) ? SUITE_DEFAULT : suites);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }
}

