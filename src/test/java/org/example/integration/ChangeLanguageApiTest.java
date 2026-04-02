package org.example.integration;

import io.qameta.allure.*;
import org.example.test_data.Constants;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Feature("Смена языка")
@Tag("api")
public class ChangeLanguageApiTest extends BaseApiTest {

    @Test
    @Story("Негативный сценарий")
    @DisplayName("Смена языка отображения на русский")
    @Description("Проверка поведения системы при смене языка на русский")
    public void shouldNotFoundWithRussianLanguage() {
        given()
                .when()
                .get(Constants.ABOUT_US_ENDPOINT)
                .then()
                .statusCode(200);
        given()
                .log().ifValidationFails()
                .when()
                .get(Constants.ABOUT_US_RU_ENDPOINT)
                .then()
                .statusCode(404)
                .contentType("text/html; charset=UTF-8")
                .body("html.head.title", containsString(Constants.TITLE_404))
                .body(containsString(Constants.CONTENT_404_EN));
    }
}