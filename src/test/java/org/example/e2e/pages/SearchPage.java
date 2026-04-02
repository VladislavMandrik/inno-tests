package org.example.e2e.pages;

import org.example.test_data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = Constants.SELECTOR_SEARCH_CONTAINER)
    private WebElement searchContainer;

    @FindBy(css = Constants.SELECTOR_SEARCH_RESULTS)
    private List<WebElement> searchResults;

    @FindBy(css = Constants.SELECTOR_RESULTS_TITLE)
    private WebElement resultsTitle;

    @FindBy(css = Constants.SELECTOR_FIND_RESULTS_HEADERS)
    private List<WebElement> resultsHeaders;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void waitForSearchContainer() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchContainer));
            assertTrue(isSearchContainerDisplayed(), Constants.ERROR_SEARCH_CONTAINER_NOT_DISPLAYED);
        } catch (Exception e) {
            fail(Constants.ERROR_SEARCH_CONTAINER_NOT_FOUND + e.getMessage());
        }
    }

    public void checkResultsHeader() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        assertTrue(areSearchResultsDisplayed(), Constants.ERROR_SEARCH_RESULTS_NOT_DISPLAYED);

        List<String> headers = wait.until(ExpectedConditions.visibilityOfAllElements(resultsHeaders))
                .stream()
                .limit(5)
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .toList();

        assertFalse(headers.isEmpty(), Constants.ERROR_HEADERS);

        boolean found = headers.stream()
                .anyMatch(header -> header.toLowerCase().contains(Constants.SEARCH_KEYWORD.toLowerCase()));

        assertTrue(found, String.format(Constants.ERROR_CONTENT_HEADERS,
                headers.size(), Constants.SEARCH_KEYWORD, headers));
    }

    public void checkEmptySearchContainer() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(searchContainer));
        } catch (TimeoutException e) {
            fail(Constants.ERROR_EMPTY_CONTAINER);
        }
    }

    public void verifySearchResults() {
        waitForSearchContainer();
        checkResultsHeader();
    }

    public boolean isSearchContainerDisplayed() {
        try {
            return searchContainer.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean areSearchResultsDisplayed() {
        return !searchResults.isEmpty() &&
                searchResults.stream().allMatch(WebElement::isDisplayed);
    }
}