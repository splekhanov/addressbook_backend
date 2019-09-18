package com.addressbook.integration.base;

import com.addressbook.integration.model.security.Credentials;
import com.addressbook.integration.model.security.Role;
import com.addressbook.integration.model.security.User;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import static io.restassured.RestAssured.given;

@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @LocalServerPort
    public int randomPort;

    public String resource(String resource) {
        return "http://localhost:" + randomPort + "/api/v1" + resource;
    }

    public String getUrl(){
        return "http://localhost:" + randomPort + "/api/v1";
    }


    public Credentials createNewUser(String name, String pass, String role) {
        User user = User.builder().name(name).password(pass).enabled(true).roles(Arrays.asList(new Role(role))).build();
        String loginUri = "http://localhost:" + randomPort + "/api/v1/registration";
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .baseUri(loginUri)
                .body(user)
                .log().all()
                .when()
                .filter(new ErrorLoggingFilter())
                .post()
                .then();
        if (response.extract().statusCode() != 201) {
            throw new RuntimeException("User " + user.getName() + " has not been created!");
        }
        return new Credentials(user.getName(), user.getPassword());
    }

    public String login(Credentials credentials) {
        String loginUri = "http://localhost:" + randomPort + "/api/v1/login";
        String token;
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .baseUri(loginUri)
                .body(credentials)
                .log().all()
                .when()
                .filter(new ErrorLoggingFilter())
                .post()
                .then();

        JsonPath jsonPathEvaluator = response.extract().body().jsonPath();
        token = jsonPathEvaluator.get("access_token");
        if (token == null) {
            throw new RuntimeException("Can't get auth token for user " + credentials.getUsername()
                    + ". Please check that user exist and credentials are correct");
        } else {
            return token;
        }
    }
}
