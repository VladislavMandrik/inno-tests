package org.example.utils.helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.config.Constants;

public final class ApiTestHelper {

    private ApiTestHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    private static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Constants.Environment.BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("API: GET запрос на {endpoint}")
    public static Response get(String endpoint) {
        return RestAssured
                .given(getRequestSpec())
                .when()
                .get(endpoint);
    }

    @Step("API: GET запрос на {endpoint} с параметрами {params}")
    public static Response get(String endpoint, java.util.Map<String, ?> params) {
        return RestAssured
                .given(getRequestSpec())
                .queryParams(params)
                .when()
                .get(endpoint);
    }

    @Step("API: POST запрос на {endpoint}")
    public static Response post(String endpoint, Object body) {
        return RestAssured
                .given(getRequestSpec())
                .body(body)
                .when()
                .post(endpoint);
    }

    @Step("API: Поиск по ключевому слову: {keyword}")
    public static Response search(String keyword) {
        return get(Constants.Endpoints.MAIN_PAGE,
                java.util.Map.of(Constants.Api.QueryParams.SEARCH, keyword));
    }

    public static void setupBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }
}