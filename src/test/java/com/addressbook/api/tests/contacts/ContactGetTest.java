package com.addressbook.api.tests.contact;

import com.addressbook.api.model.ErrorDetails;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.dto.security.CredentialsDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static com.addressbook.api.tests.user.UserBaseTest.registerNewUserAndLogin;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactGetTest extends ContactBaseTest {

    @Test
    public void testUserCanGetOnlyContactHeOwns() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .firstName("Dutch")
                .lastName("Miller")
                .phone("(981)134-31-12")
                .email("miller@yahoo.com")
                .build();

        Response addContactResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(HttpStatus.CREATED.value(), addContactResponse.status());
        ContactDTO createdContact = toPojo(addContactResponse, ContactDTO.class);

        //Add one more user, who don't own contacts at all
        CredentialsDTO credentials = new CredentialsDTO("OtherUser", "12345");
        TokenDTO otherUsersToken = registerNewUserAndLogin(credentials);

        //First user can get his contact
        Response getContactUserOwns = getContactById(token, createdContact.getId());
        assertEquals(HttpStatus.OK.value(), getContactUserOwns.status());

        //User can't get other user's contact
        Response getOtherUsersContact = getContactById(otherUsersToken, createdContact.getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), getOtherUsersContact.status());
    }
}
