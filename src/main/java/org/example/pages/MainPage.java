package org.example.pages;

import io.qameta.allure.Step;
import org.example.config.Constants;
import org.example.utils.helpers.PageActionsHelper;
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

    @Step("Клик по иконке поиска")
    public MainPage clickSearchIcon() {
        PageActionsHelper.click(wait, searchIcon);
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
}