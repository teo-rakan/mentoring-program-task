package com.epam;

import com.epam.listeners.AnnotationTransformer;
import com.epam.listeners.TestListener;
import com.epam.tests.mobile.HomePageGlobalVerificationTest;
import io.appium.java_client.service.local.AppiumDriverLocalService;
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
        //LOGGER.info("Browser: " + System.getProperty("browser.name"));
        runner.run();
    }

    void run() {
        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildDefaultService();
        List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        listeners.add(TestListener.class);
        listeners.add(AnnotationTransformer.class);

        testNG.setListenerClasses(listeners);
        testNG.setTestClasses(new Class[] { HomePageGlobalVerificationTest.class });

        appiumService.start();

        testNG.run();

        appiumService.stop();
    }
}
