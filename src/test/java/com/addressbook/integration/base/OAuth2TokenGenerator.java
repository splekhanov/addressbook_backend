//package com.addressbook.integration.base;
//
//import com.addressbook.integration.model.security.AuthToken;
//import io.restassured.response.Response;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import static io.restassured.RestAssured.given;
//
//public class OAuth2TokenGenerator {
//
//    static AuthToken getAccessToken(String username) {
//        String user = username;
//        String body;
//        JSONObject jsonObject;
//        Response response =
//                given().relaxedHTTPSValidation().auth().preemptive().basic(credentials.getClientId(), credentials.getClientSecret())
//                        .formParam("grant_type", credentials.grantType)
//                        .formParam("username", userName)
//                        .formParam("password", credentials.getPassword())
//                        .formParam("scope", credentials.getScope())
//                        .when()
//                        .post(tokenUri)
//
//        body = response.getBody().asString()
//        try {
//            jsonObject = new JSONObject(body)
//        } catch (JSONException e) {
//            throw new JSONException("Couldn't create json object using body '$body'")
//        }
//        checkTextDoesNotContainIgnoreCaseValue(body, "error")
//        String accessToken = jsonObject.get("access_token").toString()
//        String tokenType = jsonObject.get("token_type").toString()
//        LOGGER.info("Oauth Token for '{}' with type '{}' is {}", userName, tokenType, accessToken)
//        return new AuthToken(accessToken, tokenType)
//    }
//}
//TODO ALLOCATE TOKEN GENERATION WITH A FACTORY
