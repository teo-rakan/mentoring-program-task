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

    private static Option getBaseUrlOption() {
        return new Option("bu", BASE_URL_OPTION, true,
                "Set base URL value. Default: " + BASE_URL_DEFAULT);
    }

    private static Option getBrowserOption() {
        return new Option("bn", BROWSER_NAME_OPTION, true,
                "Set browser name for Mobile: iOS, Android. For Web: Safari, IE, Chrome.");
    }

    public static void parseCmdLineArgs(String[] args) {
        Options options = new Options();
        options.addOption(getBaseUrlOption());
        options.addOption(getBrowserOption());

        try {
            CommandLine cl = new GnuParser().parse(options, args);
            String baseUrl = cl.getOptionValue(BASE_URL_OPTION, BASE_URL_DEFAULT);
            String browser = cl.getOptionValue(BROWSER_NAME_OPTION, BROWSER_NAME_DEFAULT);

            Configuration.setBaseUrl(baseUrl);
            Configuration.setBrowserName(browser);
        } catch (ParseException var4) {
            LOGGER.error(var4.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.setWidth(128);
            formatter.printHelp(" ", options);
        }

    }

}

