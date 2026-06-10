package org.example.utils.helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.config.Constants;
import net.datafaker.Faker;

import java.util.Map;

public final class ApiTestHelper {

    private ApiTestHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    private static RequestSpecification getRequestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("API: GET запрос на {endpoint}")
    public static Response get(String endpoint) {
        return RestAssured
                .given(getRequestSpec(Constants.Environment.INNOWISE_URL))
                .when()
                .get(endpoint);
    }

    @Step("API: GET запрос на {endpoint} с параметрами {params}")
    public static Response get(String endpoint, Map<String, ?> params) {
        return RestAssured
                .given(getRequestSpec(Constants.Environment.INNOWISE_URL))
                .queryParams(params)
                .when()
                .get(endpoint);
    }

    @Step("API: POST запрос на {endpoint}")
    public static Response post(String endpoint, Object body) {
        return RestAssured
                .given(getRequestSpec(Constants.Environment.INNOWISE_URL))
                .body(body)
                .when()
                .post(endpoint);
    }

    @Step("API: Поиск по ключевому слову: {keyword}")
    public static Response search(String keyword) {
        return get(Constants.Endpoints.MAIN_PAGE,
                Map.of(Constants.Api.QueryParams.SEARCH, keyword));
    }

    @Step("API: AJAX поиск по ключевому слову: {keyword}")
    public static Response searchAjax(String keyword) {
        return RestAssured
                .given(getRequestSpec(Constants.Environment.INNOWISE_URL))
                .header(Constants.Headers.ACCEPT, Constants.MimeTypes.APPLICATION_JSON_TEXT)
                .header(Constants.Headers.X_REQUESTED_WITH, Constants.Headers.XML_HTTP_REQUEST)
                .header(Constants.Headers.REFERER, Constants.Environment.INNOWISE_URL + Constants.Paths.SEARCH_REFERER + keyword)
                .queryParam(Constants.Api.QueryParams.ACTION, Constants.Api.QueryParams.ALM_GET_POSTS)
                .queryParam(Constants.Api.QueryParams.SEARCH, keyword)
                .queryParam(Constants.Api.QueryParams.POST_TYPE, Constants.Api.QueryParams.POST_TYPE_VALUE)
                .queryParam(Constants.Api.QueryParams.POSTS_PER_PAGE, Constants.Api.QueryParams.POSTS_PER_PAGE_VALUE)
                .queryParam(Constants.Api.QueryParams.ORDERBY, Constants.Api.QueryParams.ORDERBY_VALUE)
                .when()
                .get(Constants.Endpoints.AJAX);
    }

    @Step("API: получение токена аутентификации")
    public static Response getAuthToken(String username, String password) {
        return RestAssured
                .given(getRequestSpec(Constants.Environment.LOCALHOST_URL))
                .body(Map.of(Constants.USERNAME, username,
                        Constants.PASSWORD, password))
                .when()
                .post(Constants.Endpoints.LOGIN_PAGE_ENDPOINT);
    }

    @Step("API: Регистрация случайного пользователя с ролью: {role}")
    public static Response registerRandomUser(String role) {
        Faker faker = new Faker();
        return RestAssured
                .given(getRequestSpec(Constants.Environment.LOCALHOST_URL))
                .body(Map.of(
                        Constants.USERNAME, faker.internet().username(),
                        Constants.PASSWORD, faker.internet().password(),
                        Constants.ROLE, role))
                .when()
                .post(Constants.Endpoints.REGISTRATION_ENDPOINT);
    }
}