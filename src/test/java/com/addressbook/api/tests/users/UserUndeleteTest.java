package com.addressbook.api.tests.user;

import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@Slf4j
public class UserUndeleteTest extends UserBaseTest {

    UserDTO user;
    TokenDTO token;

    @BeforeEach
    public void createUser() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO(generateRandomUsername(), String.valueOf(generateRandomUserId()));
        token = registerNewUserAndLogin(credentials);
        user = getUserByName(credentials, token);
    }

    @Test
    public void testUserCanBeUndeleted() throws IOException {
        Response userDeleteResponse = userClient.deleteUserById(token.getAccess_token(), user.getId());
        assertEquals(HttpStatus.OK.value(), userDeleteResponse.status());


        Response userUndeleteResponse = userClient.undeleteUser(token.getAccess_token(), user.getId());
        assertEquals(HttpStatus.OK.value(), userUndeleteResponse.status());
    }

    @Test
    public void testIfUndeleteActiveUserUnauthorizedExceptionReturns() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO("Daniel", "12345");
        TokenDTO token = registerNewUserAndLogin(credentials);
        UserDTO createdUser = getUserByName(credentials, token);

        Response userUndeleteResponse = userClient.undeleteUser(token.getAccess_token(), createdUser.getId());
        assertEquals(HttpStatus.BAD_REQUEST.value(), userUndeleteResponse.status());
    }

}
