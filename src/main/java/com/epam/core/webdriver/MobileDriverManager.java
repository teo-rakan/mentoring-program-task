package com.epam.core.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

import static com.epam.utils.PropertyManager.get;
import static com.epam.utils.PropertyManager.getKeysWithPrefix;

abstract class MobileDriverManager extends DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(MobileDriverManager.class);

    DesiredCapabilities loadCapabilitiesFromProperties(String platform) {
        String prefix = platform + ".";
        List<String> propertyKeys = getKeysWithPrefix(prefix);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        LOGGER.info("Target platform is " + platform);
        for (String key : propertyKeys) {
            String capabilityKey = key.substring(key.indexOf(".") + 1);
            String capabilityValue = get(key);
            capabilities.setCapability(capabilityKey, capabilityValue);
            LOGGER.info(String.format("Set capability: %s=%s", capabilityKey, capabilityValue));
        }
        return capabilities;
    }
}
