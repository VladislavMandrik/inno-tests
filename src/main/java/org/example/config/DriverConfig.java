package org.example.config;

import org.example.utils.driver.Browser;
import org.openqa.selenium.PageLoadStrategy;

import java.time.Duration;

public final class DriverConfig {

    private DriverConfig() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    private static final class PropertyKeys {
        static final String BROWSER = "browser";
        static final String HEADLESS = "headless";
        static final String EXPLICIT_WAIT = "wait.timeout";
        static final String REMOTE_URL = "remote.url";
    }

    public static Duration getImplicitWaitTimeout() {
        return Duration.ZERO;
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

    public static Duration getPollingInterval() {
        return Duration.ofMillis(ConfigReader.getInt(Constants.ConfigKeys.TIMEOUT_POLLING, 200));
    }

    public static PageLoadStrategy getPageLoadStrategy() {
        return PageLoadStrategy.EAGER;
    }

    public static Browser getBrowser() {
        return Browser.fromString(ConfigReader.getString(
                Constants.ConfigKeys.BROWSER, Constants.DEFAULT_BROWSER));
    }

    public static boolean isHeadless() {
        return ConfigReader.getBoolean(Constants.ConfigKeys.HEADLESS, false);
    }

    public static int getBrowserWidth() {
        return ConfigReader.getInt(Constants.ConfigKeys.BROWSER_WIDTH, Constants.BROWSER_WIDTH);
    }

    public static int getBrowserHeight() {
        return ConfigReader.getInt(Constants.ConfigKeys.BROWSER_HEIGHT, Constants.BROWSER_HEIGHT);
    }

    public static boolean isRemote() {
        return System.getProperty(PropertyKeys.REMOTE_URL) != null;
    }

    public static String getRemoteUrl() {
        return System.getProperty(PropertyKeys.REMOTE_URL, Constants.Environment.DEFAULT_SELENIUM_HUB_URL);
    }
}