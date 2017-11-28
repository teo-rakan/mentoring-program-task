package com.epam;

import com.epam.core.Configuration;
import com.epam.core.cli.CliOptions;
import com.epam.core.testng.AnnotationTransformer;
import com.epam.core.testng.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.core.Configuration.isMobile;

public class Runner {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    TestNG testNG = new TestNG();

    public static void main(String[] args) {
        CliOptions.parseCmdLineArgs(args);
        Runner runner = isMobile() ? new MobileRunner() : new Runner();
        runner.run();
    }

    List<Class<? extends ITestNGListener>> getListeners() {
        List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        listeners.add(TestListener.class);
        listeners.add(AnnotationTransformer.class);
        return listeners;
    }

    void run() {
        LOGGER.info("Desktop Runner");
        testNG.setListenerClasses(getListeners());
        testNG.setTestSuites(Arrays.asList(Configuration.getSuites()));
        testNG.run();
    }
}
