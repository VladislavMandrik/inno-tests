package org.example.pages;

import io.qameta.allure.Step;
import org.example.config.Constants;
import org.example.utils.helpers.PageActionsHelper;
import org.example.utils.helpers.WaitHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    @FindBy(css = Constants.Selectors.SEARCH_LOGO)
    private WebElement searchLogo;

    @FindBy(css = Constants.Selectors.SEARCH_ICON)
    private WebElement searchIcon;

    @FindBy(css = Constants.Selectors.SEARCH_INPUT)
    private WebElement searchInput;

    @FindBy(css = Constants.Selectors.FOOTER_ABOUT_US_LINK)
    private List<WebElement> footerLinks;

    @FindBy(css = Constants.Selectors.FOOTER_PHONE_LINKS)
    private List<WebElement> footerPhoneLinks;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие главной страницы")
    public MainPage open() {
        driver.get(Constants.Environment.BASE_URL + Constants.Endpoints.MAIN_PAGE);
        PageActionsHelper.waitForPageReady(wait);
        return this;
    }

    @Step("Клик по иконке поиска")
    public MainPage clickSearchIcon() {
        PageActionsHelper
                .waitForClickable(wait, searchIcon)
                .click();
        return this;
    }

    @Step("Ввод поискового запроса: {query}")
    public MainPage typeSearchQuery(String query) {
        PageActionsHelper.clearAndSendKeys(wait, searchInput, query);
        return this;
    }

    @Step("Нажатие Enter в поле поиска")
    public void pressEnterInSearchField() {
        PageActionsHelper
                .waitForVisibility(wait, searchInput)
                .sendKeys(Keys.ENTER);
    }

    @Step("Получение плейсхолдера поля поиска")
    public String getSearchFieldPlaceholder() {
        PageActionsHelper.waitForVisibility(wait, searchInput);
        return searchInput.getAttribute(Constants.Attributes.PLACEHOLDER);
    }

    @Step("Проверка отображения логотипа")
    public boolean isLogoDisplayed() {
        return PageActionsHelper.isDisplayed(searchLogo);
    }

    @Step("Ожидание загрузки главной страницы")
    public MainPage waitForPageLoaded() {
        PageActionsHelper.waitForVisibility(wait, searchLogo);
        return this;
    }

    @Step("Проверка отображения поля поиска")
    public boolean isSearchInputDisplayed() {
        return PageActionsHelper.isDisplayed(searchInput);
    }

    public List<WebElement> getAllFooterLinks() {
        List<WebElement> allLinks = new ArrayList<>();
        allLinks.addAll(footerLinks);
        allLinks.addAll(footerPhoneLinks);
        return allLinks;
    }

    @Step("Открытие главной страницы и ожидание загрузки")
    public static MainPage openAndWait(WebDriver driver) {
        return new MainPage(driver)
                .open()
                .waitForPageLoaded();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WaitHelper getWait() {
        return wait;
    }
}