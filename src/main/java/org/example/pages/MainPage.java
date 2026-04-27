package org.example.pages;

import io.qameta.allure.Step;
import org.example.config.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
        waitForPageReady();
        return this;
    }

    @Step("Клик по иконке поиска")
    public MainPage clickSearchIcon() {
        waitForClickable(searchIcon).click();
        return this;
    }

    @Step("Ввод поискового запроса: {query}")
    public MainPage typeSearchQuery(String query) {
        type(searchInput, query);
        return this;
    }

    @Step("Нажатие Enter в поле поиска")
    public void pressEnterInSearchField() {
        waitForVisibility(searchInput).sendKeys(Keys.ENTER);
    }

    @Step("Получение плейсхолдера поля поиска")
    public String getSearchFieldPlaceholder() {
        waitForVisibility(searchInput);
        return searchInput.getAttribute(Constants.Attributes.PLACEHOLDER);
    }

    @Step("Выполнение поиска: {query}")
    public SearchPage performSearch(String searchQuery) {
        waitForVisibility(searchLogo);
        clickSearchIcon();
        typeSearchQuery(searchQuery);
        pressEnterInSearchField();
        return new SearchPage(driver);
    }

    @Step("Проверка отображения логотипа")
    public boolean isLogoDisplayed() {
        return isDisplayed(searchLogo);
    }

    @Step("Ожидание загрузки главной страницы")
    public MainPage waitForPageLoaded() {
        waitForVisibility(searchLogo);
        return this;
    }

    @Step("Проверка отображения поля поиска")
    public boolean isSearchInputDisplayed() {
        return isDisplayed(searchInput);
    }

    @Step("Поиск ссылки в футере по href: {href}")
    public WebElement getFooterLink(String href) {
        return getAllFooterLinks().stream()
                .filter(link -> {
                    String linkHref = link.getDomAttribute("href");
                    return linkHref != null && linkHref.contains(href);
                })
                .findFirst()
                .orElse(null);
    }

    private List<WebElement> getAllFooterLinks() {
        List<WebElement> allLinks = new ArrayList<>();
        allLinks.addAll(footerLinks);
        allLinks.addAll(footerPhoneLinks);
        return allLinks;
    }

    @Step("Получение текста ссылки в футере: {href}")
    public String getFooterLinkText(String href) {
        WebElement link = getFooterLink(href);

        if (link == null) {
            throw new NoSuchElementException(Constants.Errors.FOOTER_LINK_NOT_FOUND + href);
        }

        return getText(link).trim();
    }

    @Step("Открытие главной страницы и ожидание загрузки")
    public static MainPage openAndWait(WebDriver driver) {
        return new MainPage(driver)
                .open()
                .waitForPageLoaded();
    }
}