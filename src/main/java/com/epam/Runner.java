package com.epam;

import com.epam.core.cli.CliOptions;
import com.epam.core.testng.AnnotationTransformer;
import com.epam.core.testng.TestListener;
import com.epam.tests.desktop.HomePageHeaderVerificationTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    TestNG testNG = new TestNG();

    Runner() {
    }

    public static void main(String[] args) {
        CliOptions.parseCmdLineArgs(args);
        Runner runner = new Runner();
        LOGGER.info("Desktop Runner");
        runner.run();
    }

    List<Class<? extends ITestNGListener>> getListeners() {
        List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        listeners.add(TestListener.class);
        listeners.add(AnnotationTransformer.class);
        return listeners;
    }

    void run() {
        testNG.setListenerClasses(getListeners());
        testNG.setTestClasses(new Class[]{HomePageHeaderVerificationTest.class});
        testNG.run();
    }
}
