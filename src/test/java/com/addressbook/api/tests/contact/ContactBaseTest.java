package com.addressbook.api.tests.contact;

import com.addressbook.api.base.BaseTest;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static com.addressbook.api.tests.user.UserBaseTest.getUserByName;
import static com.addressbook.api.tests.user.UserBaseTest.registerNewUserAndLogin;

public class ContactBaseTest extends BaseTest {

    protected UserDTO user;
    protected TokenDTO token;

    @BeforeEach
    public void createUser() throws IOException {
        CredentialsDTO credentials = new CredentialsDTO(generateRandomUsername(), "12345");
        token = registerNewUserAndLogin(credentials);
        user = getUserByName(credentials, token);
    }
}
