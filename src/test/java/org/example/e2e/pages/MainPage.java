package org.example.e2e.pages;

import org.example.test_data.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.fail;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = Constants.SELECTOR_SEARCH_LOGO)
    private WebElement searchLogo;

    @FindBy(css = Constants.SELECTOR_SEARCH_ICON)
    private WebElement searchIcon;

    @FindBy(css = Constants.SELECTOR_INPUT_FIELD)
    private WebElement inputField;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void waitLogo() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchLogo));
        } catch (TimeoutException e) {
            fail(Constants.ERROR_LOGO_NOT_DISPLAYED);
        }
    }

    public void clickSearchIcon() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
            searchIcon.click();
        } catch (TimeoutException e) {
            fail(Constants.ERROR_ICON_WITH_TIMEOUT);
        }
    }

    public void inputText(String searchQuery) {
        wait.until(ExpectedConditions.visibilityOf(inputField));
        inputField.clear();
        inputField.sendKeys(searchQuery);
        inputField.sendKeys(Keys.ENTER);
    }

    public void searchText(String searchQuery) {
        waitLogo();
        clickSearchIcon();
        inputText(searchQuery);
    }
}