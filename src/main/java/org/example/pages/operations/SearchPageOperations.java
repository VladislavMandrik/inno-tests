package org.example.pages.operations;

import io.qameta.allure.Step;
import org.example.pages.SearchPage;
import org.example.utils.helpers.PageActionsHelper;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPageOperations {

    private final SearchPage searchPage;

    public SearchPageOperations(WebDriver driver) {
        this.searchPage = new SearchPage(driver);
    }

    @Step("Проверка наличия результатов поиска")
    public boolean hasResults() {
        PageActionsHelper.waitForVisibilityAll(searchPage.getWait(), searchPage.getSearchResults());
        return !searchPage
                        .getSearchResults()
                        .isEmpty()
                &&
                searchPage
                        .getSearchResults()
                        .stream()
                        .anyMatch(PageActionsHelper::isDisplayed);
    }

    @Step("Получение заголовков результатов")
    public List<String> getResultsHeaders() {
        PageActionsHelper.waitForVisibilityAll(searchPage.getWait(), searchPage.getResultsHeaders());
        return searchPage
                .getResultsHeaders()
                .stream()
                .filter(PageActionsHelper::isDisplayed)
                .map(e -> PageActionsHelper.getText(searchPage.getWait(), e))
                .filter(text -> !text.isEmpty())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Step("Проверка содержания ключевого слова в заголовках")
    public boolean headersContainKeyword(String keyword) {
        List<String> headers = getResultsHeaders();
        return headers.stream()
                .anyMatch(header -> header.toLowerCase().contains(keyword.toLowerCase()));
    }
}