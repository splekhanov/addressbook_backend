package com.addressbook.integration.base;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @LocalServerPort
    public int randomPort;

    protected String authToken;

    @BeforeClass
    public void setToken() throws JSONException {
        setAuthToken(login());
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String login() throws JSONException {
        String baseURI = "http://localhost:" + randomPort + "/api/v1/login";
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "flash");
        requestBody.put("password", "1234");
        ValidatableResponse response =  given()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .body(requestBody.toString())
                .log().all()
                .when()
                .filter(new ErrorLoggingFilter())
                .post()
                .then();

        JsonPath jsonPathEvaluator = response.extract().body().jsonPath();
        return (String) jsonPathEvaluator.get("access_token");
    }
}
