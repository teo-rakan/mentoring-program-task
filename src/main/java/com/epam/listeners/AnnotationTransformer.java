package com.epam.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void transform(ITestAnnotation annotation, Class aClass,
                          Constructor constructor, Method method) {
        annotation.setAlwaysRun(true);
        LOGGER.debug("Set 'alwaysRun'=true for " + method.getName());
    }
}
