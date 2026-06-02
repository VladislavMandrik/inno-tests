package org.example.utils.helpers;

import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageActionsHelper {
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();

    private PageActionsHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static void init(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, DriverConfig.getExplicitWaitTimeout(), Constants.POLLING_INTERVAL);
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        waitThreadLocal.set(wait);
    }

    private static WebDriverWait getWait() {
        return waitThreadLocal.get();
    }

    public static WebElement waitForVisibility(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> waitForVisibilityAll(List<WebElement> elements) {
        return getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static WebElement waitForClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitForInvisibility(WebElement element) {
        return getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitForInvisibilityAll(List<WebElement> elements) {
        return getWait().until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public static boolean waitForPageReady() {
        return getWait().until(driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return Constants.JS.COMPLETE.equals(js.executeScript(Constants.JS.READY_STATE));
        });
    }

    public static void waitForPageFullyLoaded() {
        waitForPageReady();
    }

    public static boolean waitForScrollAt(long expectedPosition) {
        return getWait().until(driver -> {
            Object result = ((JavascriptExecutor) driver).executeScript(Constants.JS.SCROLL_POSITION);
            return ((Number) result).longValue() == expectedPosition;
        });
    }

    public static void click(WebElement element) {
        waitForClickable(element).click();
    }

    public static void clearAndSendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
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

    public static void scrollBy(WebDriver driver, int pixels) {
        executeScript(driver, String.format("window.scrollBy(0, %d);", pixels));
    }
}