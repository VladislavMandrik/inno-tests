package org.example.pages.operations;

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
}