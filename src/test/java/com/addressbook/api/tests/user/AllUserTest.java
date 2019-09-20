package com.addressbook.api.tests.user;

import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import com.addressbook.api.base.BaseTest;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.addressbook.api.tests.user.UserBaseTest.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@Slf4j
public class AllUserTest extends UserBaseTest {

    @Test
    public void testUserCanRegister() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Elvis","12345");
        Response newUserResponse = registerNewUser(credentials);
        assertEquals(newUserResponse.status(), HttpStatus.CREATED.value());
    }

    @Test
    public void testFailToRegisterIfUserWithTheSameNameAlreadyExists() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("James","12345");
        Response userRegResponse = registerNewUser(credentials);
        assertEquals(userRegResponse.status(), HttpStatus.CREATED.value());
        Response userSecondRegResponse = registerNewUser(credentials);
        assertEquals(userSecondRegResponse.status(), HttpStatus.CONFLICT.value());
    }

    @Test
    public void testUserCanLoginIfExists() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Curt","12345");
        Response userRegResponse = registerNewUser(credentials);
        assertEquals(userRegResponse.status(), HttpStatus.CREATED.value());
        TokenDTO token = login(credentials);
        assertNotNull(token.getAccess_token(),"Token hasn't been received");
    }

    @Test
    public void testUserCanBeDeleted() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("David","12345");
        TokenDTO token = registerNewUserAndLogIn(credentials);
        UserDTO createdUser  = getUserByName(credentials, token);
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), HttpStatus.OK.value());
    }

    @Test
    public void testUserCanBeUndeleted() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Margo","12345");
        TokenDTO token = registerNewUserAndLogIn(credentials);
        UserDTO createdUser  = getUserByName(credentials, token);
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), HttpStatus.OK.value());

        Response userUndeleteResponse = userClient.undeleteUser(token.getAccess_token(), createdUser.getId());
        assertEquals(userUndeleteResponse.status(), HttpStatus.OK.value());
    }

    @Test
    public void testIfUndeleteActiveUserBadRequestExceptionReturns() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Daniel","12345");
        TokenDTO token = registerNewUserAndLogIn(credentials);
        UserDTO createdUser  = getUserByName(credentials, token);

        Response userUndeleteResponse = userClient.undeleteUser(token.getAccess_token(), createdUser.getId());
        assertEquals(userUndeleteResponse.status(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testIfUserAlreadyDeletedNotFoundExceptionReturns() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Bill","12345");
        TokenDTO token = registerNewUserAndLogIn(credentials);
        UserDTO createdUser  = getUserByName(credentials, token);
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), HttpStatus.OK.value());

        Response userSecondDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userSecondDeleteResponse.status(), HttpStatus.NOT_FOUND.value());
    }
}
