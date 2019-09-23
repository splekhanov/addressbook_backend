package com.addressbook.api.tests.users;

import com.addressbook.api.model.ErrorDetails;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetTest extends UserBaseTest {

    TokenDTO token;

    @BeforeEach
    public void getToken() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO(generateRandomUsername(), "12345");
        token = registerNewUserAndLogin(credentials);
    }

    @Test
    public void testIfGetNonexistentUserByNameNotFoundExceptionReturns() throws IOException {
        String nonExistentUserName = generateRandomUsername();
        Response getUserResponse = userClient.getUserByName(token.getAccess_token(),nonExistentUserName);
        ErrorDetails error = toPojo(getUserResponse, ErrorDetails.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), getUserResponse.status());
        assertEquals(error.getMessage(), String.format("User with name '%s' not found!", nonExistentUserName));
    }

    @Test
    public void testIfGetNonexistentUserByIdNotFoundExceptionReturns() throws IOException {
        Long nonExistentUserId = generateRandomUserId();

        Response getUserResponse = userClient.getUserById(token.getAccess_token(),nonExistentUserId);
        ErrorDetails error = toPojo(getUserResponse, ErrorDetails.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), getUserResponse.status());
        assertEquals(error.getMessage(), String.format("User with name '%d' not found!", nonExistentUserId));
    }
}
