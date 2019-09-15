package com.addressbook.integration.base;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ResponseService {

    static ValidatableResponse sendPostRequest(RequestSpecification request) {
        return given()
                .relaxedHTTPSValidation()
                .spec(request)
                .when()
                .post()
                .then();
    }

    static ValidatableResponse sendGetRequest(RequestSpecification request) {
        return given()
                .relaxedHTTPSValidation()
                .spec(request)
                .when()
                .filter(new ErrorLoggingFilter())
                .get()
                .then();
    }
}
