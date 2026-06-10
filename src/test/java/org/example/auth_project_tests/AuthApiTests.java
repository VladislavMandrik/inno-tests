package org.example.auth_project_tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.api.BaseApiTest;
import org.example.config.Constants;
import org.example.enums.Role;
import org.example.utils.helpers.ApiTestHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.example.config.Constants.TOKEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Аутентификация")
@Tag("api")
@Tag("smoke")
@DisplayName("API: Проверка аутентификации")
class AuthApiTests extends BaseApiTest {
    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена - позитивный сценарий")
    @Description("Проверка получения токена - позитивный сценарий")
    void shouldAuthenticate(Role role) {
        Response response = ApiTestHelper.getAuthToken(role.getUsername(), role.getPassword());
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.body().jsonPath().getString(TOKEN), notNullValue());
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена при невалидном логине")
    @Description("Проверка получения токена при невалидном логине")
    void shouldNotAuthenticateWithInvalidLogin(Role role) {
        Response response = ApiTestHelper.getAuthToken(Constants.INVALID_USERNAME, role.getPassword());
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена при невалидном пароле")
    @Description("Проверка получения токена при невалидном пароле")
    void shouldNotAuthenticateWithInvalidPassword(Role role) {
        Response response = ApiTestHelper.getAuthToken(role.getUsername(), Constants.INVALID_PASSWORD);
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @Test
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена при невалидном логине и пароле")
    @Description("Проверка получения токена при невалидном логине и пароле")
    void shouldNotAuthenticateWithInvalidCredentials() {
        Response response = ApiTestHelper.getAuthToken(Constants.INVALID_USERNAME, Constants.INVALID_PASSWORD);
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена при пустом логине")
    @Description("Проверка получения токена при пустом логине")
    void shouldNotAuthenticateWithEmptyLogin(Role role) {
        Response response = ApiTestHelper.getAuthToken(Constants.EMPTY_STRING, role.getPassword());
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена при пустом пароле")
    @Description("Проверка получения токена при пустом пароле")
    void shouldNotAuthenticateWithEmptyPassword(Role role) {
        Response response = ApiTestHelper.getAuthToken(role.getUsername(), Constants.EMPTY_STRING);
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @Test
    @Story("Страница авторизации")
    @DisplayName("API: Проверка получения токена при пустом логине и пароле")
    @Description("Проверка получения токена при пустом логине и пароле")
    void shouldNotAuthenticateWithEmptyCredentials() {
        Response response = ApiTestHelper.getAuthToken(Constants.EMPTY_STRING, Constants.EMPTY_STRING);
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: SQL инъекция в логине — 401")
    @Description("Проверка защиты от SQL инъекций через поле логина")
    void shouldNotAuthenticateWithSQLInjection(Role role) {
        Response response = ApiTestHelper.getAuthToken(Constants.TestData.SQL_INJECTION, role.getPassword());
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Страница авторизации")
    @DisplayName("API: XSS скрипт в пароле — 401")
    @Description("Проверка защиты от XSS атак через поле пароля")
    void shouldNotAuthenticateWithXSS(Role role) {
        Response response = ApiTestHelper.getAuthToken(role.getUsername(), Constants.TestData.XSS_SCRIPT);
        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
    }
}