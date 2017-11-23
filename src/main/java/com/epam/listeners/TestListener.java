package com.epam.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.debug(iTestResult.getName() + " started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.debug( iTestResult.getName() + ": SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.debug( iTestResult.getName() + ": FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.debug( iTestResult.getName() + ": SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOGGER.debug( iTestResult.getName() + ": FAILED with success percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.debug("===== " + iTestContext.getName() + " =====" );
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.debug("======================================" );
    }
}
