package com.epam;

import com.epam.core.Configuration;
import com.epam.core.cli.CliOptions;
import com.epam.tests.mobile.HomePageGlobalVerificationTest;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class MobileRunner extends Runner {

    private static final Logger LOGGER = LogManager.getLogger(MobileRunner.class);

    public static void main(String[] args) {
        CliOptions.parseCmdLineArgs(args);
        Runner runner = new MobileRunner();
        LOGGER.info("Mobile Runner");
        runner.run();
    }

    AppiumDriverLocalService buildAppiumService() {
        return new AppiumServiceBuilder()
                .withLogFile(new File("./logs/appium.log"))
                .withIPAddress("127.0.0.1")
                .build();
    }

    void run() {
        AppiumDriverLocalService appiumService = buildAppiumService();
        String appiumUrl;

        testNG.setListenerClasses(getListeners());
        testNG.setTestClasses(new Class[]{HomePageGlobalVerificationTest.class});

        appiumService.start();

        appiumUrl = appiumService.getUrl().toString();
        Configuration.setAppiumURL(appiumUrl);
        LOGGER.info("Appium URL: " + appiumUrl);
        try {
            testNG.run();
        } finally {
            appiumService.stop();
        }
    }
}
