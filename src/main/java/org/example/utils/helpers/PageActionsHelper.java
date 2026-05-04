package org.example.utils.helpers;

import org.example.config.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageActionsHelper {

    private PageActionsHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static WebElement waitForVisibility(WaitHelper wait, WebElement element) {
        return wait.waitForVisibility(element);
    }

    public static boolean waitForInvisibility(WaitHelper wait, WebElement element) {
        return wait.waitForInvisibility(element);
    }

    public static List<WebElement> waitForVisibilityAll(WaitHelper wait, List<WebElement> elements) {
        return wait.waitForVisibilityAll(elements);
    }

    public static WebElement waitForClickable(WaitHelper wait, WebElement element) {
        return wait.waitForClickable(element);
    }

    public static void waitForPageReady(WaitHelper wait) {
        wait.waitForPageReady();
    }

    public static void click(WaitHelper wait, WebElement element) {
        wait.waitForClickable(element).click();
    }

    public static void clearAndSendKeys(WaitHelper wait, WebElement element, String text) {
        waitForVisibility(wait, element);
        element.clear();
        element.sendKeys(text);
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static String getText(WaitHelper wait, WebElement element) {
        return waitForVisibility(wait, element).getText();
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