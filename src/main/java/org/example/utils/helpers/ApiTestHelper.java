package org.example.utils.helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.config.Constants;

import java.util.Map;

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
                Map.of(Constants.Api.QueryParams.SEARCH, keyword));
    }

    @Step("API: AJAX поиск по ключевому слову: {keyword}")
    public static Response searchAjax(String keyword) {
        return RestAssured
                .given(getRequestSpec())
                .header(Constants.Headers.ACCEPT, Constants.MimeTypes.APPLICATION_JSON_TEXT)
                .header(Constants.Headers.X_REQUESTED_WITH, Constants.Headers.XML_HTTP_REQUEST)
                .header(Constants.Headers.REFERER, Constants.Environment.BASE_URL + Constants.Paths.SEARCH_REFERER + keyword)
                .queryParam(Constants.Api.QueryParams.ACTION, Constants.Api.QueryParams.ALM_GET_POSTS)
                .queryParam(Constants.Api.QueryParams.SEARCH, keyword)
                .queryParam(Constants.Api.QueryParams.POST_TYPE, Constants.Api.QueryParams.POST_TYPE_VALUE)
                .queryParam(Constants.Api.QueryParams.POSTS_PER_PAGE, Constants.Api.QueryParams.POSTS_PER_PAGE_VALUE)
                .queryParam(Constants.Api.QueryParams.ORDERBY, Constants.Api.QueryParams.ORDERBY_VALUE)
                .when()
                .get(Constants.Endpoints.AJAX);
    }

    public static void setupBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }
}