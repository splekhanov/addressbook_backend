package com.addressbook.api.tests.user;

import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@Slf4j
public class UserUndeleteTest extends UserBaseTest {

    @Test
    public void testUserCanBeUndeleted() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Margo", "12345");
        TokenDTO token = registerNewUserAndLogIn(credentials);
        UserDTO createdUser = getUserByName(credentials, token);
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), createdUser.getId());
        assertEquals(userDeleteResponse.status(), HttpStatus.OK.value());

        Response userUndeleteResponse = userClient.undeleteUser(token.getAccess_token(), createdUser.getId());
        assertEquals(userUndeleteResponse.status(), HttpStatus.OK.value());
    }

    @Test
    public void testIfUndeleteActiveUserBadRequestExceptionReturns() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Daniel", "12345");
        TokenDTO token = registerNewUserAndLogIn(credentials);
        UserDTO createdUser = getUserByName(credentials, token);

        Response userUndeleteResponse = userClient.undeleteUser(token.getAccess_token(), createdUser.getId());
        assertEquals(userUndeleteResponse.status(), HttpStatus.BAD_REQUEST.value());
    }

}
