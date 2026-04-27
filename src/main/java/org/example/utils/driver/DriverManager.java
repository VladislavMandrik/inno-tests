package org.example.utils.driver;

import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            Browser browser = DriverConfig.getBrowser();
            WebDriver driver = BrowserFactory.createDriver(browser);
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    public static boolean isDriverActive() {
        return driverThreadLocal.get() != null;
    }
}