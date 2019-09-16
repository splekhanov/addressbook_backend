package com.addressbook.integration.base;

import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecProvider {

    public static RequestSpecification buildRequestSpec(String url, String token) {

        PreemptiveOAuth2HeaderScheme oAuth2Scheme = new PreemptiveOAuth2HeaderScheme();
        oAuth2Scheme.setAccessToken(token);

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setAuth(oAuth2Scheme)
                .addFilter(new ErrorLoggingFilter())
                .log(LogDetail.ALL)
                .build();
    }
}
