//package com.addressbook.integration.base;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.Properties;
//
//final class AuthTokenFactory {
//
//    private static final ThreadLocal<AuthToken> AUTH_TOKEN = new ThreadLocal<AuthToken>();
//
//    private AuthTokenFactory() {
//
//    }
//
//    static void setToken(AuthToken authToken) {
//        AUTH_TOKEN.set(authToken);
//    }
//
//    static AuthToken getToken() {
//        AuthToken tkn = AUTH_TOKEN.get()
//        if (!tkn) {
//            Credentials credentials = generateCredentials();
//            tkn = getAccessToken(credentials);
//            setToken(tkn);
//        }
//        return tkn;
//    }
//
//    static Credentials generateCredentials(String credFile = getValueOf("credentials")) {
//        Properties credentialProperties = PropertiesLoader.loadFrom(credFile)
//        return new Credentials(credentialProperties)
//    }
//}
//TODO ALLOCATE TOKEN GENERATION WITH A FACTORY
