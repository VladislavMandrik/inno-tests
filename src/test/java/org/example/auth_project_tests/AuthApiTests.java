package org.example.auth_project_tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.api.BaseApiTest;
import org.example.config.Constants;
import org.example.config.EnvConfig;
import org.example.utils.helpers.ApiTestHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Аутентификация")
@Tag("api")
@Tag("smoke")
@DisplayName("API: Проверка аутентификации")
class AuthApiTests extends BaseApiTest {
    private static final String TOKEN = "token";

    @ParameterizedTest(name = "#{index}")
    @MethodSource("provideCredentials")
    @Story("Страница авторизации")
    @DisplayName("API: Успешное получение JWT токена")
    @Description("Проверка получения токена с разными зарегистрированными пользователями")
    void shouldAuthenticateWithDifferentUsers(String username, String password) {
        Response response = ApiTestHelper.getAuthToken(username, password);
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.body().jsonPath().getString(TOKEN), notNullValue());
    }

    static Stream<Arguments> provideCredentials() {
        return Stream.of(Arguments.of(
                        EnvConfig.get(Constants.Env.ADMIN_USERNAME),
                        EnvConfig.get(Constants.Env.ADMIN_PASSWORD)
                ),
                Arguments.of(
                        EnvConfig.get(Constants.Env.USER_USERNAME),
                        EnvConfig.get(Constants.Env.USER_PASSWORD)
                )
        );
    }

//    @ParameterizedTest(name = "#{index}")
//    @MethodSource("provideInvalidCredentials")
//    @Story("Страница авторизации")
//    @DisplayName("API: Ошибка в получении JWT токена")
//    @Description("Проверка получения токена с разными зарегистрированными пользователями с неверными данными")
//    void shouldNotAuthenticateWithDifferentUsers(String username, String password) {
//        Response response = ApiTestHelper.getAuthToken(username, password);
//        assertThat(response.statusCode(), is(HttpStatus.SC_UNAUTHORIZED));
//    }
//
//    static Stream<Arguments> provideInvalidCredentials() {
//        return Stream.of(Arguments.of(EnvConfig.get(Constants.Env.ADMIN_USERNAME), "invalidPassword"),
//                Arguments.of("invalidLogin", EnvConfig.get(Constants.Env.ADMIN_PASSWORD)),
//                Arguments.of(EnvConfig.get(Constants.Env.USER_USERNAME), "invalidPassword"),
//                Arguments.of("invalidLogin", EnvConfig.get(Constants.Env.USER_PASSWORD)),
//                Arguments.of("invalidLogin", "invalidPassword"),
//                Arguments.of(EnvConfig.get(Constants.Env.ADMIN_USERNAME), ""),
//                Arguments.of("", EnvConfig.get(Constants.Env.ADMIN_PASSWORD)),
//                Arguments.of(EnvConfig.get(Constants.Env.USER_USERNAME), ""),
//                Arguments.of("", EnvConfig.get(Constants.Env.USER_PASSWORD)),
//                Arguments.of("", "")
//        );
//    }
}