package org.example.utils.helpers;

import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageActionsHelper {
    private static final ThreadLocal<WaitHelper> waitThreadLocal = new ThreadLocal<>();

    private PageActionsHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static void init(WebDriver driver) {
        waitThreadLocal.set(new WaitHelper(driver, DriverConfig.getExplicitWaitTimeout()));
    }

    public static WebElement waitForVisibility(WebElement element) {
        return waitThreadLocal
                .get()
                .waitForVisibility(element);
    }

    public static boolean waitForInvisibility(WebElement element) {
        return waitThreadLocal
                .get()
                .waitForInvisibility(element);
    }

    public static List<WebElement> waitForVisibilityAll(List<WebElement> elements) {
        return waitThreadLocal
                .get()
                .waitForVisibilityAll(elements);
    }

    public static WebElement waitForClickable(WebElement element) {
        return waitThreadLocal
                .get()
                .waitForClickable(element);
    }

    public static void waitForPageReady() {
        waitThreadLocal
                .get()
                .waitForPageReady();
    }

    public static void click(WebElement element) {
        waitThreadLocal
                .get()
                .waitForClickable(element)
                .click();
    }

    public static void clearAndSendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public static void sendKeys(WebElement element, CharSequence... keys) {
        waitForVisibility(element).sendKeys(keys);
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    public static void takeScreenshot(WebDriver driver, String name) {
        AllureHelper.attachScreenshot(driver, name);
    }

    public static Object executeScript(WebDriver driver, String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    public static Object executeScript(WebDriver driver, String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        executeScript(driver, Constants.JS.SCROLL_INTO_VIEW, element);
    }

    public static void scrollToTop(WebDriver driver) {
        executeScript(driver, Constants.JS.SCROLL_TO_TOP);
    }

    public static void scrollToBottom(WebDriver driver) {
        executeScript(driver, Constants.JS.SCROLL_TO_BOTTOM);
    }
}