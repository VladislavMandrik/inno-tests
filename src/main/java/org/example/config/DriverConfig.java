package org.example.config;

import org.example.utils.driver.Browser;
import org.openqa.selenium.PageLoadStrategy;

import java.time.Duration;

public final class DriverConfig {
    private static final String DEFAULT_BROWSER = "chrome";

    private DriverConfig() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static Duration getExplicitWaitTimeout() {
        return Duration.ofSeconds(ConfigReader.getInt(Constants.ConfigKeys.TIMEOUT_EXPLICIT, 15));
    }

    public static Duration getPageLoadTimeout() {
        return Duration.ofSeconds(ConfigReader.getInt(Constants.ConfigKeys.TIMEOUT_PAGE, 30));
    }

    public static Duration getScriptTimeout() {
        return Duration.ofSeconds(ConfigReader.getInt(Constants.ConfigKeys.TIMEOUT_SCRIPT, 20));
    }

    public static PageLoadStrategy getPageLoadStrategy() {
        return PageLoadStrategy.EAGER;
    }

    public static Browser getBrowser() {
        return Browser.fromString(ConfigReader.getString(
                Constants.ConfigKeys.BROWSER, DEFAULT_BROWSER));
    }

    public static boolean isHeadless() {
        return ConfigReader.getBoolean(Constants.ConfigKeys.HEADLESS, false);
    }
}