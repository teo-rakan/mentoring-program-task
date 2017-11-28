package com.epam.core.testng;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;


public class TestListener implements ITestListener, ISuiteListener {

    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.debug("=======================================");
        LOGGER.debug(iTestResult.getName() + ": STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.debug(iTestResult.getName() + ": SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info(iTestResult.getName() + ": FAILED");
        LOGGER.error(ExceptionUtils.getStackTrace(iTestResult.getThrowable()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.debug(iTestResult.getName() + ": SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOGGER.debug(iTestResult.getName() + ": FAILED with success percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    @Override
    public void onStart(ISuite iSuite) {
        LOGGER.debug("=======================================");
        LOGGER.debug("SUITE: " + iSuite.getName());
        LOGGER.debug("=======================================");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LOGGER.debug("=======================================");
        LOGGER.debug("End of suite ==========================");
        LOGGER.debug("=======================================");

    }
}
