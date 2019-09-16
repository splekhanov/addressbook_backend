package com.addressbook.integration.base;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ResponseService {

    public static ValidatableResponse sendPostRequest(RequestSpecification request, Object body) {
        return given()
                .relaxedHTTPSValidation()
                .spec(request)
                .body(body)
                .when()
                .post()
                .then();
    }

    public static ValidatableResponse sendGetRequest(RequestSpecification request) {
        return given()
                .relaxedHTTPSValidation()
                .spec(request)
                .when()
                .filter(new ErrorLoggingFilter())
                .get()
                .then();
    }
}
