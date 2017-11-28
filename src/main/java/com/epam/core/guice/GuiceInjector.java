package com.epam.core.guice;

import com.epam.core.Configuration;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;


public class GuiceInjector {

    private static final Logger LOGGER = LogManager.getLogger(GuiceInjector.class);
    private static final String PACKAGE = "com.epam.core.guice.modules.";
    private static Injector injector;

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    private static Class<? extends Module> findModule(String modulePartName) {
        String className = PACKAGE + modulePartName + "Module";

        Class<? extends Module> module = null;
        try {
            module = (Class<? extends Module>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find module: " + className);
        }

        return module;
    }

    private static Module createInstance(Class<? extends Module> moduleClass) {
        Module module = null;

        try {
            Constructor constructor = moduleClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            module = (Module) constructor.newInstance();
            LOGGER.debug("Instance of " + moduleClass + " was created");
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Cannot create instance of " + moduleClass + ": "
                    + e.getMessage());
        }
        return module;
    }

    public static Injector get() {
        if (injector == null) {
            String platform = Configuration.getTargetPlatform();
            String browser = Configuration.getBrowserName();
            String targetTestEnvironment = capitalize(platform) + capitalize(browser);
            Class<? extends Module> moduleClass = findModule(targetTestEnvironment);
            Module module = createInstance(moduleClass);

            try {
                injector = Guice.createInjector(module);
            } catch (Exception ex) {
                LOGGER.error("Cannot create injector: " + ex.getMessage());
            }
        }
        return injector;
    }
}