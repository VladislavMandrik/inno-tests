package org.example.utils.helpers;

import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitHelper {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this(driver, DriverConfig.getExplicitWaitTimeout());
    }

    public WaitHelper(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout, Constants.POLLING_INTERVAL);
        this.wait.ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForVisibilityAll(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForInvisibility(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitForPageReady() {
        return wait.until(driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return Constants.JS.COMPLETE.equals(js.executeScript(Constants.JS.READY_STATE));
        });
    }

    public void waitForPageFullyLoaded() {
        waitForPageReady();
    }
}