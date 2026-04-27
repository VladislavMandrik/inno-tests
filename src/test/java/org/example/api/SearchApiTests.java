package org.example.api;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.config.Constants;
import org.example.utils.helpers.ApiTestHelper;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Поиск")
@Tag("api")
@DisplayName("API: Поиск по сайту")
public class SearchApiTests extends BaseApiTest {

    @Test
    @Story("Успешный поиск")
    @DisplayName("GET /?s=test → 200 OK, результаты найдены")
    @Description("Проверка поиска по ключевому слову 'test'")
    public void shouldReturnSearchResultsForValidKeyword() {
        Response response = ApiTestHelper.search(Constants.TestData.SEARCH_KEYWORD);

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), containsString(Constants.Api.DEFAULT_CONTENT_TYPE));
        assertThat(response.body().asString(),
                containsString(String.format(Constants.TestData.SEARCH_PATTERN,
                        Constants.TestData.SEARCH_KEYWORD)));
    }

    @Test
    @Story("Негативный сценарий")
    @DisplayName("GET /?s= → 200 OK, страница поиска без результатов")
    @Description("Проверка поведения при пустом поисковом запросе")
    public void shouldShowSearchPageWithEmptyQuery() {
        Response response = ApiTestHelper.search("");

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is(Constants.Api.HTML_UTF8));
        assertThat(response.body().asString(), allOf(
                containsString(Constants.TestData.SEARCH_FIELD),
                containsString(Constants.TestData.SEARCH_RESULTS_TEXT),
                containsString(Constants.TestData.CONTENT_MASONRY)

        ));
    }
}