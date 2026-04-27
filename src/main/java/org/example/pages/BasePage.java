package org.example.pages;

import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.example.utils.helpers.AllureHelper;
import org.example.utils.helpers.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitHelper wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver, DriverConfig.getExplicitWaitTimeout());
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.waitForVisibility(element);
    }

    protected boolean waitForInvisibility(WebElement element) {
        return wait.waitForInvisibility(element);
    }

    protected List<WebElement> waitForVisibilityAll(List<WebElement> elements) {
        return wait.waitForVisibilityAll(elements);
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.waitForClickable(element);
    }

    protected void waitForPageReady() {
        wait.waitForPageReady();
    }

    protected void click(WebElement element) {
        wait.waitForClickable(element).click();
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    protected void takeScreenshot(String name) {
        AllureHelper.attachScreenshot(driver, name);
    }

    protected Object executeScript(String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    protected Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    protected void scrollToElement(WebElement element) {
        executeScript(Constants.JS.SCROLL_INTO_VIEW, element);
    }

    protected void scrollToTop() {
        executeScript(Constants.JS.SCROLL_TO_TOP);
    }

    protected void scrollToBottom() {
        executeScript(Constants.JS.SCROLL_TO_BOTTOM);
    }
}