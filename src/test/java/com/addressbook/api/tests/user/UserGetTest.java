package com.addressbook.api.tests.user;

import com.addressbook.api.model.ErrorDetails;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetTest extends UserBaseTest {

    @Test
    public void testIfGetNonexistentUserByNameNotFoundExceptionReturns() throws IOException {
        String nonExistentUserName = "someUsergfgdfgdf";
        CredentialsDTO credentials = new CredentialsDTO("Addie", "12345");
        TokenDTO token = registerNewUserAndLogin(credentials);

        Response getUserResponse = userClient.getUserByName(token.getAccess_token(),nonExistentUserName);
        ErrorDetails error = toPojo(getUserResponse, ErrorDetails.class);
        assertEquals(getUserResponse.status(), HttpStatus.NOT_FOUND.value());
        assertEquals(error.getMessage(), String.format("User with name '%s' not found!", nonExistentUserName));
    }

    @Test
    public void testIfGetNonexistentUserByIdNotFoundExceptionReturns() throws IOException {
        Long nonExistentUserId = 434344234L;
        CredentialsDTO credentials = new CredentialsDTO("Tod", "12345");
        TokenDTO token = registerNewUserAndLogin(credentials);

        Response getUserResponse = userClient.getUserById(token.getAccess_token(),nonExistentUserId);
        ErrorDetails error = toPojo(getUserResponse, ErrorDetails.class);
        assertEquals(getUserResponse.status(), HttpStatus.NOT_FOUND.value());
        assertEquals(error.getMessage(), String.format("User with name '%d' not found!", nonExistentUserId));
    }
}
