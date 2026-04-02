package org.example.integration;

import io.qameta.allure.*;
import org.example.test_data.Constants;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Feature("Поиск")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("api")
public class SearchApiTests extends BaseApiTest {

    @Test
    @Order(1)
    @Story("Успешный поиск")
    @DisplayName("Успешный поиск с валидными данными")
    @Description("Проверка поиска по ключевому слову")
    public void shouldSuccessfulSearch() {
        given()
                .queryParam("s", Constants.SEARCH_KEYWORD)
                .log().params()
                .when()
                .get(Constants.MAIN_PAGE_ENDPOINT)
                .then()
                .statusCode(200)
                .contentType("text/html; charset=UTF-8")
                .body(containsString(String.format(Constants.SEARCH_PATTERN, Constants.SEARCH_KEYWORD)))
                .log().ifValidationFails();
    }

    @Test
    @Order(2)
    @Story("Негативный сценарий поиска")
    @DisplayName("Поиск с пустым запросом")
    @Description("Проверка поведения системы при отправке пустого поискового запроса")
    public void shouldNotSearchWithEmptyQuery() {
        given()
                .queryParam("s", "")
                .log().params()
                .when()
                .get(Constants.MAIN_PAGE_ENDPOINT)
                .then()
                .statusCode(200)
                .contentType("text/html; charset=UTF-8")
                .body(containsString("search-field"))
                .body(containsString("Search results"))
                .body(containsString("content-masonry"))
                .log().ifValidationFails();
    }
}