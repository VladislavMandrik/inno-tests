package org.example.ui.operations;

import io.qameta.allure.Step;
import org.example.config.Constants;
import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.example.utils.helpers.PageActionsHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageOperations {

    private final MainPage mainPage;

    public MainPageOperations(WebDriver driver) {
        this.mainPage = new MainPage(driver);
    }

    @Step("Открытие главной страницы")
    public MainPageOperations open() {
        mainPage
                .getDriver()
                .get(Constants.Environment.BASE_URL + Constants.Endpoints.MAIN_PAGE);
        PageActionsHelper.waitForPageReady(mainPage.getWait());
        return this;
    }

    @Step("Выполнение поиска: {query}")
    public SearchPage performSearch(String searchQuery) {
        mainPage.clickSearchIcon();
        mainPage.typeSearchQuery(searchQuery);
        mainPage.pressEnterInSearchField();
        return new SearchPage(mainPage.getDriver());
    }

    @Step("Поиск ссылки в футере по href: {href}")
    public WebElement getFooterLink(String href) {
        return mainPage.getAllFooterLinks().stream()
                .filter(link -> {
                    String linkHref = link.getDomAttribute("href");
                    return linkHref != null && linkHref.contains(href);
                })
                .findFirst()
                .orElse(null);
    }

    @Step("Получение текста ссылки в футере: {href}")
    public String getFooterLinkText(String href) {
        WebElement link = getFooterLink(href);

        if (link == null) {
            throw new NoSuchElementException(Constants.Errors.FOOTER_LINK_NOT_FOUND + href);
        }

        return PageActionsHelper
                .getText(mainPage.getWait(), link)
                .trim();
    }

    @Step("Открытие главной страницы и ожидание загрузки")
    public MainPageOperations openAndWait() {
        open();
        mainPage.waitForPageLoaded();
        return this;
    }
}