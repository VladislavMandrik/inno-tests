package org.example.auth_project_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.example.api.BaseApiTest;
import org.example.config.Constants;
import org.example.enums.Role;
import org.example.utils.helpers.ApiTestHelper;
import org.example.utils.helpers.DatabaseHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import io.qameta.allure.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static org.example.config.Constants.TOKEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Регистрация")
@Tag("api")
@Tag("e2e")
@DisplayName("API: Проверка регистрации")
class RegistrationApiTests extends BaseApiTest {
    private String username;

    @AfterEach
    public void cleanUp() {
        if (username != null) {
            DatabaseHelper.deleteUser(username);
        }
    }

    @ParameterizedTest(name = "Пользователь: {0}")
    @EnumSource(Role.class)
    @Story("Регистрация")
    @DisplayName("API: Регистрация → получение токена → проверка БД")
    @Description("Полный сценарий: регистрация пользователя, получение JWT токена, проверка записи в БД")
    void shouldRegisterAndGetToken(Role role) {
        Response response = ApiTestHelper.registerRandomUser(role.getRoleName());
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));

        username = response.jsonPath().getString(Constants.USERNAME);
        var password = response.jsonPath().getString(Constants.PASSWORD);
        var user = DatabaseHelper.getUser(username);

        Response tokenResponse = ApiTestHelper.getAuthToken(username, password);
        assertThat(tokenResponse.statusCode(), is(HttpStatus.SC_OK));
        assertThat(tokenResponse.body().jsonPath().getString(TOKEN), notNullValue());
        assertThat(user, notNullValue());
        assertThat(user.id(), greaterThan(0));
        assertThat(user.username(), is(username));
        assertThat(user.password(), is(password));
        assertThat(user.role(), is(role.getRoleName()));
    }
}