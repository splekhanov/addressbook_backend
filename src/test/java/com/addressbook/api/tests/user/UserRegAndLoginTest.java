package com.addressbook.api.tests.user;

import com.addressbook.api.model.ErrorDetails;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.RoleDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRegAndLoginTest extends UserBaseTest {

    @Test
    public void testUserCanRegister() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Elvis", "12345");
        Response newUserResponse = registerNewUser(credentials);
        assertEquals(newUserResponse.status(), HttpStatus.CREATED.value());

        TokenDTO token = loginAndGetToken(credentials);
        UserDTO createdUser = getUserByName(credentials, token);
        RoleDTO role = new RoleDTO(1L, "admin");
        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getName(), "Elvis");
        assertTrue(createdUser.isEnabled());
        assertEquals(createdUser.getRoles(), Arrays.asList(role));
    }

    @Test
    public void testFailToRegisterIfUserWithTheSameNameAlreadyExists() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("James", "12345");
        Response userRegResponse = registerNewUser(credentials);
        assertEquals(userRegResponse.status(), HttpStatus.CREATED.value());
        Response userSecondRegResponse = registerNewUser(credentials);
        assertEquals(userSecondRegResponse.status(), HttpStatus.CONFLICT.value());
    }

    @Test
    public void testUserCanLoginIfExists() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Curt", "12345");
        Response userRegResponse = registerNewUser(credentials);
        assertEquals(userRegResponse.status(), HttpStatus.CREATED.value());
        TokenDTO token = loginAndGetToken(credentials);
        assertNotNull(token.getAccess_token(), "Token hasn't been received");
    }

    @Test
    public void testLoginWithWrongPasswordReturnsException() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Damien", "12345");
        Response userRegResponse = registerNewUser(credentials);
        assertEquals(userRegResponse.status(), HttpStatus.CREATED.value());

        CredentialsDTO wrongCredentials = new CredentialsDTO("Damien", "123456");
        Response loginResponse = login(wrongCredentials);
        ErrorDetails error = toPojo(loginResponse, ErrorDetails.class);
        assertEquals(loginResponse.status(), HttpStatus.UNAUTHORIZED.value());
        assertEquals(error.getMessage(), "Invalid login or password");
    }

    @Test
    public void testLoginWithWrongUsernameReturnsException() throws IOException {
        CredentialsDTO wrongCredentials = new CredentialsDTO("nonexistentUser", "12345");
        Response loginResponse = login(wrongCredentials);
        ErrorDetails error = toPojo(loginResponse, ErrorDetails.class);
        assertEquals(loginResponse.status(), HttpStatus.UNAUTHORIZED.value());
        assertEquals(error.getMessage(), "Invalid login or password");
    }
}
