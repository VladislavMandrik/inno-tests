package org.example.pages;

import io.qameta.allure.Step;
import org.example.config.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    @FindBy(css = Constants.Selectors.SEARCH_CONTAINER)
    private WebElement searchContainer;

    @FindBy(css = Constants.Selectors.SEARCH_RESULTS)
    private List<WebElement> searchResults;

    @FindBy(css = Constants.Selectors.SEARCH_RESULTS_TITLE)
    private WebElement resultsTitle;

    @FindBy(css = Constants.Selectors.SEARCH_RESULTS_HEADERS)
    private List<WebElement> resultsHeaders;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы результатов")
    public SearchPage waitForSearchContainer() {
        waitForVisibility(searchContainer);
        return this;
    }

    @Step("Проверка наличия результатов поиска")
    public boolean hasResults() {
        waitForVisibilityAll(searchResults);
        return !searchResults.isEmpty() && searchResults.stream()
                .anyMatch(this::isDisplayed);
    }

    @Step("Получение заголовков результатов")
    public List<String> getResultsHeaders() {
        waitForVisibilityAll(resultsHeaders);
        return resultsHeaders.stream()
                .filter(this::isDisplayed)
                .map(this::getText)
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

    @Step("Проверка отображения контейнера результатов")
    public boolean isSearchContainerDisplayed() {
        return isDisplayed(searchContainer);
    }

    @Step("Проверка пустого контейнера результатов")
    public boolean isSearchContainerEmpty() {
        return !isDisplayed(searchContainer);
    }

    @Step("Ожидание скрытия контейнера поиска")
    public boolean waitForSearchContainerHidden() {
        return waitForInvisibility(searchContainer);
    }
}