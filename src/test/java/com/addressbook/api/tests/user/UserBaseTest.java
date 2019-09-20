package com.addressbook.api.tests.user;

import com.addressbook.api.base.BaseTest;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import java.io.IOException;


public class UserBaseTest extends BaseTest {

    public Response registerNewUser(CredentialsDTO credentials){
        UserDTO userDTO = UserDTO.builder().name(credentials.getUsername()).password(credentials.getPassword()).build();
        Response userRegResponse = userClient.register(userDTO);
        return userRegResponse;
    }

    public TokenDTO login(CredentialsDTO credentials) throws IOException {
        Response userLoginResponse = userClient.login(credentials);
        return toPojo(userLoginResponse.body().asReader(), TokenDTO.class);
    }

    public TokenDTO registerNewUserAndLogIn(CredentialsDTO credentials) throws IOException {
        registerNewUser(credentials);
        return login(credentials);
    }

    public UserDTO getUserByName(CredentialsDTO credentials, TokenDTO token) throws IOException {
        Response userGetByNameResponse = userClient.getUserByName(token.getAccess_token(), credentials.getUsername());
        return toPojo(userGetByNameResponse.body().asReader(), UserDTO.class);
    }
}
