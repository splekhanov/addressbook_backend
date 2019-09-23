package com.addressbook.api.tests.contacts;

import com.addressbook.api.model.ErrorDetails;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.dto.security.CredentialsDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static com.addressbook.api.tests.users.UserBaseTest.registerNewUserAndLogin;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactDeleteTest extends ContactBaseTest {

    @Test
    public void testUserCanDeleteContact() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .firstName("Anna")
                .lastName("Smith")
                .phone("(981)134-31-12")
                .email("anna@yahoo.com")
                .build();
        Response addContactResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(HttpStatus.CREATED.value(), addContactResponse.status());

        ContactDTO createdContact = toPojo(addContactResponse, ContactDTO.class);
        Response deleteContactResponse = contactClient.deleteContactById(token.getAccess_token(), createdContact.getId());
        assertEquals(HttpStatus.OK.value(), deleteContactResponse.status());
    }

    @Test
    public void testUserCanDeleteOnlyContactHeOwns() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .firstName("Michael")
                .lastName("Stone")
                .phone("(981)134-31-12")
                .email("stonemichael@yahoo.com")
                .build();

        Response addContactResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(HttpStatus.CREATED.value(), addContactResponse.status());
        ContactDTO createdContact = toPojo(addContactResponse, ContactDTO.class);

        //Add one more user, who don't own contacts at all
        CredentialsDTO credentials = new CredentialsDTO("OtherUser", "12345");
        TokenDTO otherUsersToken = registerNewUserAndLogin(credentials);

        //Other user can't delete contact
        Response deleteOtherUsersContact = contactClient.deleteContactById(otherUsersToken.getAccess_token(), createdContact.getId());
        assertEquals(HttpStatus.NOT_FOUND.value(), deleteOtherUsersContact.status());

        //First user is able to delete his contact
        Response getContactUserOwns = getContactById(token, createdContact.getId());
        assertEquals(HttpStatus.OK.value(), getContactUserOwns.status());

    }
}
