package com.epam.core.testng;

import com.epam.core.Configuration;
import com.epam.core.guice.GuiceInjector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ITestContext;

public class MobileTestListener extends TestListener {

    private static final Logger LOGGER = LogManager.getLogger(MobileTestListener.class);

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    @Override
    public void onStart(ISuite iSuite) {
        GuiceInjector.get().injectMembers(this);
        LOGGER.debug("=======================================");
        LOGGER.debug("SUITE: " + iSuite.getName());
        LOGGER.debug("=======================================");
        try {
            driverManager.open(Configuration.getBaseUrl());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LOGGER.debug("=======================================");
        LOGGER.debug("End of suite ==========================");
        LOGGER.debug("=======================================");
        driverManager.quit();
    }
}
