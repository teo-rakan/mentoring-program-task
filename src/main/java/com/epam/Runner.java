package com.epam;

import com.epam.core.testng.AnnotationTransformer;
import com.epam.core.testng.TestListener;
import com.epam.tests.desktop.HomePageHeaderVerificationTest;
import com.epam.utils.PropertyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    protected TestNG testNG = new TestNG();

    Runner() {

    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        LOGGER.info("Desktop Runner");
        setSystemProperties();
        //LOGGER.info("Browser: " + System.getProperty("browser.name"));
        runner.run();
    }

    static void setSystemProperties() {
        System.setProperty("browser.name", PropertyManager.get("browser.name"));
    }

    void run() {
        List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        listeners.add(TestListener.class);
        listeners.add(AnnotationTransformer.class);

        testNG.setListenerClasses(listeners);
        testNG.setTestClasses(new Class[] { HomePageHeaderVerificationTest.class });
        testNG.run();
    }
}
