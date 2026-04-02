package org.example.integration;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.conn.params.ConnManagerParams;
import org.example.test_data.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApiTest {
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());

        RestAssured.config = RestAssured.config()
                .connectionConfig(ConnectionConfig.connectionConfig()
                        .closeIdleConnectionsAfterEachResponse())
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(ConnManagerParams.MAX_CONNECTIONS_PER_ROUTE, 100)
                        .setParam(ConnManagerParams.MAX_TOTAL_CONNECTIONS, 400));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterEach
    void tearDown() {
        RestAssured.reset();
    }
}
