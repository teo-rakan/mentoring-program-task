package com.epam.core.guice;


import com.epam.core.guice.modules.AndroidModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;


public class GuiceInjector {

    private static Injector injector;

    //todo synch ?
    public static Injector getInjector() {
        if (injector == null) {
            //String browserName = System.getProperty("browser.name");

            Module module = new AndroidModule(); // todo Module resolver

            injector = Guice.createInjector(module);
        }
        return injector;
    }
}