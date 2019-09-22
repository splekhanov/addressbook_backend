package com.addressbook.api.tests.user;

import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserDeleteTest extends UserBaseTest {

    protected UserDTO user;
    protected TokenDTO token;

    @BeforeEach
    public void createUser() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO(generateRandomUsername(), "12345");
        token = registerNewUserAndLogin(credentials);
        user = getUserByName(credentials, token);
    }

    @Test
    public void testUserCanBeDeleted() throws IOException {
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), user.getId());
        assertEquals(HttpStatus.OK.value(), userDeleteResponse.status());
        checkUserHasBeenDeleted();
    }

    @Test
    public void testIfUserAlreadyDeletedNotFoundExceptionReturns() throws IOException {
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), user.getId());
        assertEquals(HttpStatus.OK.value(), userDeleteResponse.status());
        checkUserHasBeenDeleted();

        Response userSecondDeleteResponse = userClient.deleteUserById(token.getAccess_token(), user.getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), userSecondDeleteResponse.status());
    }

    void checkUserHasBeenDeleted() throws IOException {
        Response deletedUserResponse = userClient.getUserById(token.getAccess_token(), user.getId());
        assertEquals(HttpStatus.OK.value(), deletedUserResponse.status());
        UserDTO deletedUser = toPojo(deletedUserResponse, UserDTO.class);
        assertFalse(deletedUser.isEnabled());
    }
}
