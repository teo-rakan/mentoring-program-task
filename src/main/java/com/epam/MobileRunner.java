package com.epam;

import com.epam.core.Configuration;
import com.epam.core.testng.AnnotationTransformer;
import com.epam.core.testng.MobileTestListener;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MobileRunner extends Runner {

    private static final Logger LOGGER = LogManager.getLogger(MobileRunner.class);

    AppiumDriverLocalService buildAppiumService() {
        return new AppiumServiceBuilder()
                .withLogFile(new File("./logs/appium.log"))
                .withIPAddress("127.0.0.1")
                .build();
    }

    List<Class<? extends ITestNGListener>> getListeners() {
        List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        listeners.add(MobileTestListener.class);
        listeners.add(AnnotationTransformer.class);
        return listeners;
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
