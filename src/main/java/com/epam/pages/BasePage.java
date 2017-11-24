package com.epam.pages;

import com.epam.core.guice.GuiceInjector;
import com.epam.core.webdriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import javax.inject.Inject;

public abstract class BasePage {

    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    @Inject
    protected DriverManager driverManager;

    @Inject
    private FieldDecorator fieldDecorator;

    protected BasePage() {
        GuiceInjector.getInjector().injectMembers(this);

        LOGGER.debug("Objects injected via Guice:");
        LOGGER.debug("Driver manager: " + driverManager.getClass().getSimpleName());
        LOGGER.debug("Field decorator: " + fieldDecorator.getClass().getSimpleName());

        PageFactory.initElements(fieldDecorator, this);
    }
}
