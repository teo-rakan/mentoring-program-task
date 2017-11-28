package com.epam;

import com.epam.core.Configuration;
import com.epam.core.cli.CliOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;

public class MobileRunner extends Runner {

    private static final Logger LOGGER = LogManager.getLogger(MobileRunner.class);

    public static void main(String[] args) {
        CliOptions.parseCmdLineArgs(args);
        Runner runner = new MobileRunner();
        runner.run();
    }

    AppiumDriverLocalService buildAppiumService() {
        return new AppiumServiceBuilder()
                .withLogFile(new File("./logs/appium.log"))
                .withIPAddress("127.0.0.1")
                .build();
    }

    @Override
    void run() {
        LOGGER.info("Mobile Runner");
        AppiumDriverLocalService appiumService = buildAppiumService();

        testNG.setListenerClasses(getListeners());
        testNG.setTestSuites(Arrays.asList(Configuration.getSuites()));

        appiumService.start();

        Configuration.setAppiumURL(appiumService.getUrl().toString());
        LOGGER.info("Appium URL: " + Configuration.getAppiumURL());
        try {
            testNG.run();
        } finally {
            appiumService.stop();
        }
    }
}
