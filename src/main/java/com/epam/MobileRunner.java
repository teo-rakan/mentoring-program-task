package com.epam;

import com.epam.core.testng.AnnotationTransformer;
import com.epam.core.testng.TestListener;
import com.epam.tests.mobile.HomePageGlobalVerificationTest;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGListener;

import java.util.ArrayList;
import java.util.List;

public class MobileRunner extends Runner {

    private static final Logger LOGGER = LogManager.getLogger(MobileRunner.class);

    public static void main(String[] args) {
        Runner runner = new MobileRunner();
        LOGGER.info("Mobile Runner");
        setSystemProperties();
        runner.run();
    }

    void run() {
        AppiumDriverLocalService appiumService;

        List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        listeners.add(TestListener.class);
        listeners.add(AnnotationTransformer.class);

        testNG.setListenerClasses(listeners);
        testNG.setTestClasses(new Class[]{HomePageGlobalVerificationTest.class});

        appiumService = new AppiumServiceBuilder().withIPAddress("127.0.0.1").build();
        appiumService.start();

        System.setProperty("appium.url", appiumService.getUrl().toString());
        LOGGER.info("Appium URL: " + appiumService.getUrl());
        try {
            testNG.run();
        } finally {
            appiumService.stop();
        }
    }
}
