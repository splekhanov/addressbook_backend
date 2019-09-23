package com.addressbook.api.tests.users;

import com.addressbook.api.base.BaseTest;
import com.addressbook.api.clients.UserClient;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class UserBaseTest extends BaseTest {

    public static Response registerNewUser(CredentialsDTO credentials) {
        UserDTO userDTO = UserDTO.builder().name(credentials.getUsername()).password(credentials.getPassword()).build();
        return userClient.register(userDTO);
    }

    public static Response login(CredentialsDTO credentials) throws IOException {
        return userClient.login(credentials);
    }

    public static TokenDTO loginAndGetToken(CredentialsDTO credentials) throws IOException {
        Response userLoginResponse = userClient.login(credentials);
        return toPojo(userLoginResponse, TokenDTO.class);
    }

    public static TokenDTO registerNewUserAndLogin(CredentialsDTO credentials) throws IOException {
        registerNewUser(credentials);
        return loginAndGetToken(credentials);
    }

    public static UserDTO getUserByName(CredentialsDTO credentials, TokenDTO token) throws IOException {
        Response userGetByNameResponse = userClient.getUserByName(token.getAccess_token(), credentials.getUsername());
        return toPojo(userGetByNameResponse, UserDTO.class);
    }
}
