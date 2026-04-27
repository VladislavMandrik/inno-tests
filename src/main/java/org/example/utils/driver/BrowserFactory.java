package org.example.utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;

public final class BrowserFactory {

    private BrowserFactory() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static WebDriver createDriver(Browser browser) {
        return createLocalDriver(browser);
    }

    private static WebDriver createLocalDriver(Browser browser) {
        WebDriver driver;

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(getEdgeOptions());
                break;

            case SAFARI:
                driver = new SafariDriver(getSafariOptions());
                break;

            default:
                throw new IllegalArgumentException(
                        String.format(Constants.Errors.UNSUPPORTED_BROWSER, browser)
                );
        }

        configureTimeouts(driver);
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                Constants.BrowserArgs.NO_SANDBOX,
                Constants.BrowserArgs.DISABLE_DEV_SHM,
                Constants.BrowserArgs.REMOTE_ALLOW_ORIGINS,
                Constants.BrowserArgs.DISABLE_GPU,
                Constants.BrowserArgs.DISABLE_EXTENSIONS
        );

        if (DriverConfig.isHeadless()) {
            options.addArguments(Constants.BrowserArgs.HEADLESS_NEW);
            options.addArguments(String.format(
                    Constants.BrowserArgs.WINDOW_SIZE,
                    DriverConfig.getBrowserWidth(),
                    DriverConfig.getBrowserHeight()
            ));
        }

        options.addArguments(Constants.BrowserArgs.DISABLE_NOTIFICATIONS);
        options.addArguments(Constants.BrowserArgs.DISABLE_INFO_BARS);

        if (isRunningInCI()) {
            options.addArguments(Constants.BrowserArgs.DISABLE_WEB_SECURITY);
            options.addArguments(Constants.BrowserArgs.ALLOW_INSECURE_CONTENT);
            options.addArguments(Constants.BrowserArgs.NO_ZYGOTE);
            options.addArguments(Constants.BrowserArgs.DISABLE_SETUID_SANDBOX);
            options.addArguments(Constants.BrowserArgs.SINGLE_PROCESS);
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put(Constants.BrowserPrefs.NOTIFICATIONS, 2);
        prefs.put(Constants.BrowserPrefs.CREDENTIALS_SERVICE, false);
        prefs.put(Constants.BrowserPrefs.PASSWORD_MANAGER, false);
        options.setExperimentalOption("prefs", prefs);

        options.setExperimentalOption("excludeSwitches",
                new String[]{Constants.BrowserArgs.EXCLUDE_AUTOMATION_SWITCH});
        options.setExperimentalOption("useAutomationExtension", false);

        options.setPageLoadStrategy(DriverConfig.getPageLoadStrategy());

        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (DriverConfig.isHeadless()) {
            options.addArguments(Constants.BrowserArgs.HEADLESS);
            options.addArguments(String.format(
                    Constants.BrowserArgs.WIDTH,
                    DriverConfig.getBrowserWidth()
            ));
            options.addArguments(String.format(
                    Constants.BrowserArgs.HEIGHT,
                    DriverConfig.getBrowserHeight()
            ));
        }

        options.addPreference(Constants.BrowserPrefs.WEB_NOTIFICATIONS, false);
        options.addPreference(Constants.BrowserPrefs.PUSH_ENABLED, false);
        options.addPreference(Constants.BrowserPrefs.DOWNLOAD_FOLDER_LIST, 2);
        options.addPreference(Constants.BrowserPrefs.NEVER_ASK_SAVE_TO_DISK,
                Constants.MimeTypes.OCTET_STREAM);

        options.setPageLoadStrategy(DriverConfig.getPageLoadStrategy());

        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (DriverConfig.isHeadless()) {
            options.addArguments(Constants.BrowserArgs.HEADLESS_NEW);
            options.addArguments(String.format(
                    Constants.BrowserArgs.WINDOW_SIZE,
                    DriverConfig.getBrowserWidth(),
                    DriverConfig.getBrowserHeight()
            ));
        }

        options.addArguments(Constants.BrowserArgs.DISABLE_NOTIFICATIONS);
        options.addArguments(Constants.BrowserArgs.REMOTE_ALLOW_ORIGINS);
        options.addArguments(Constants.BrowserArgs.NO_SANDBOX);
        options.addArguments(Constants.BrowserArgs.DISABLE_DEV_SHM);

        options.setPageLoadStrategy(DriverConfig.getPageLoadStrategy());

        return options;
    }

    private static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(false);

        options.setCapability("safari.options", Map.of(
                Constants.SafariCaps.TECHNOLOGY_PREVIEW, false,
                Constants.SafariCaps.AUTOMATIC_INSPECTION, false
        ));

        return options;
    }

    private static void configureTimeouts(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(DriverConfig.getPageLoadTimeout());
        driver.manage().timeouts().scriptTimeout(DriverConfig.getScriptTimeout());

        if (!DriverConfig.isHeadless()) {
            try {
                driver.manage().window().maximize();
            } catch (Exception e) {
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(
                        DriverConfig.getBrowserWidth(),
                        DriverConfig.getBrowserHeight()
                ));
            }
        }
    }

    private static boolean isRunningInCI() {
        return System.getenv(Constants.EnvVars.CI) != null ||
                System.getenv(Constants.EnvVars.JENKINS_HOME) != null ||
                System.getenv(Constants.EnvVars.GITHUB_ACTIONS) != null ||
                System.getenv(Constants.EnvVars.TEAMCITY_VERSION) != null ||
                System.getenv(Constants.EnvVars.GITLAB_CI) != null;
    }
}