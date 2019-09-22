package com.addressbook.api.tests.contact;

import com.addressbook.api.model.ErrorDetails;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.dto.security.CredentialsDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.addressbook.api.tests.user.UserBaseTest.registerNewUserAndLogin;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactAddTest extends ContactBaseTest {

    @Test
    public void testUserCanAddContact() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .firstName("Mike")
                .lastName("Smith")
                .phone("(981)134-31-12")
                .email("msmith@yahoo.com")
                .build();
        Response addContactResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(HttpStatus.CREATED.value(), addContactResponse.status());

        ContactDTO createdContact = toPojo(addContactResponse, ContactDTO.class);
        assertEquals(contact.getFirstName(), createdContact.getFirstName());
        assertEquals(contact.getLastName(), createdContact.getLastName());
        assertEquals(contact.getEmail(), createdContact.getEmail());
        assertEquals(contact.getPhone(), createdContact.getPhone());
    }

    @Test
    public void testFailToCreateContactWithTheSameFirstName() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .firstName("George")
                .lastName("Hannigan")
                .phone("(981)134-31-12")
                .email("george@yahoo.com")
                .build();

        Response addContactResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(HttpStatus.CREATED.value(), addContactResponse.status());

        Response addContactAgainResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(HttpStatus.CONFLICT.value(), addContactAgainResponse.status());

        ErrorDetails error = toPojo(addContactAgainResponse, ErrorDetails.class);
        assertEquals(error.getMessage(), String.format("Contact with name '%s' is already exists", contact.getFirstName()));
    }
}
