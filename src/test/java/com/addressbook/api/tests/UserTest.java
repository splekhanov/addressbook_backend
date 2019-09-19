package com.addressbook.api.tests;

import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import com.addressbook.api.base.BaseTest;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@Slf4j
public class UserTest extends BaseTest {

    @Test
    public void testUserCanRegister() throws IOException {
        UserDTO userDTO = UserDTO.builder().name("Elvis").password("12345").build();
        Response userRegResponse = userClient.register(userDTO);
        assertEquals(userRegResponse.status(), 201);
    }

    @Test
    public void testFailToRegisterIfUserWithTheSameNameAlreadyExists() throws IOException {
        UserDTO userDTO = UserDTO.builder().name("James").password("12345").build();
        Response userRegResponse = userClient.register(userDTO);
        assertEquals(userRegResponse.status(), 201);

        Response userSecondRegResponse = userClient.register(userDTO);
        assertEquals(userSecondRegResponse.status(), 409);
    }

    @Test
    public void testUserCanLoginIfExists() throws IOException {
        UserDTO userDTO = UserDTO.builder().name("Curt").password("12345").build();
        Response userRegResponse = userClient.register(userDTO);
        assertEquals(userRegResponse.status(), 201);

        CredentialsDTO credentials = new CredentialsDTO("Curt","12345");
        Response userLoginResponse = userClient.login(credentials);
        assertEquals(userLoginResponse.status(), 200);
        TokenDTO token = toPojo(userLoginResponse.body().asReader(), TokenDTO.class);
        assertNotNull(token.getAccess_token(),"Token hasn't been received");
    }

    @Test
    public void testUserCanBeDeleted() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("David","12345");
        UserDTO userDTO = UserDTO.builder().name(credentials.getUsername()).password(credentials.getPassword()).build();
        Response userRegResponse = userClient.register(userDTO);
        assertEquals(userRegResponse.status(), 201);

        Response userLoginResponse = userClient.login(credentials);
        TokenDTO token = toPojo(userLoginResponse.body().asReader(), TokenDTO.class);

        Response userGetByNameResponse = userClient.getUserByName(token.getAccess_token(), credentials.getUsername());
        UserDTO createdUser = toPojo(userGetByNameResponse.body().asReader(), UserDTO.class);

        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), 204);
    }
}
