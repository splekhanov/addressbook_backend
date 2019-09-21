package com.addressbook.api.tests.user;

import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDeleteTest extends UserBaseTest {

    @Test
    public void testUserCanBeDeleted() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("David", "12345");
        TokenDTO token = registerNewUserAndLogin(credentials);
        UserDTO createdUser = getUserByName(credentials, token);
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), HttpStatus.OK.value());
    }

    @Test
    public void testIfUserAlreadyDeletedNotFoundExceptionReturns() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Bill", "12345");
        TokenDTO token = registerNewUserAndLogin(credentials);
        UserDTO createdUser = getUserByName(credentials, token);
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), HttpStatus.OK.value());

        Response userSecondDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userSecondDeleteResponse.status(), HttpStatus.NOT_FOUND.value());
    }
}
