package org.example.pages;

import io.qameta.allure.Step;
import org.example.config.Constants;
import org.example.utils.helpers.PageActionsHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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
        PageActionsHelper.waitForVisibility(wait, searchContainer);
        return this;
    }

    @Step("Ожидание скрытия контейнера поиска")
    public boolean waitForSearchContainerHidden() {
        return PageActionsHelper.waitForInvisibility(wait, searchContainer);
    }

    public List<WebElement> getSearchResults() { return searchResults; }
    public List<WebElement> getResultsHeaders() { return resultsHeaders; }
}