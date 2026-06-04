package org.example.api;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.config.Constants;
import org.example.utils.helpers.ApiTestHelper;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Доступность страниц")
@Tag("api")
@Tag("smoke")
@DisplayName("API: Проверка доступности страниц")
class PageAvailabilityApiTest extends BaseApiTest {

    @Test
    @Story("Главная страница")
    @DisplayName("GET / → 200 OK")
    @Description("Проверка доступности главной страницы")
    void shouldAccessMainPage() {
        Response response = ApiTestHelper.get(Constants.Endpoints.MAIN_PAGE);
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    @Story("About Us")
    @DisplayName("GET /about-us/ → 200 OK")
    @Description("Проверка доступности страницы About Us на английском")
    void shouldAccessAboutUsInEnglish() {
        Response response = ApiTestHelper.get(Constants.Endpoints.ABOUT_US);
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.body().asString(), containsString(Constants.Content.ABOUT_US_KEYWORD));
    }

    @Test
    @Story("About Us")
    @DisplayName("GET /ru/about-us/ → 404 Not Found")
    @Description("Проверка что русская версия About Us не существует")
    void shouldReturnNotFoundForRussianAboutUs() {
        Response response = ApiTestHelper.get(Constants.Endpoints.ABOUT_US_RU);
        assertThat(response.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        assertThat(response.body().asString(), allOf(
                containsString(Constants.Errors.TITLE_404),
                containsString(Constants.Errors.CONTENT_404_EN)));
    }
}