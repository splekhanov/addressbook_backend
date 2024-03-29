package com.addressbook.api.tests.contacts;

import com.addressbook.api.base.BaseTest;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static com.addressbook.api.tests.users.UserBaseTest.getUserByName;
import static com.addressbook.api.tests.users.UserBaseTest.registerNewUserAndLogin;

public class ContactBaseTest extends BaseTest {

    protected UserDTO user;
    protected TokenDTO token;

    @BeforeEach
    public void createUser() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO(generateRandomUsername(), "12345");
        token = registerNewUserAndLogin(credentials);
        user = getUserByName(credentials, token);
    }

    public static Response getContactById(TokenDTO token, Long id) throws IOException {
        return contactClient.getContactById(token.getAccess_token(), id);
    }
}
