package com.addressbook.api.tests.contact;

import com.addressbook.dto.contact.ContactDTO;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddContactTest extends ContactBaseTest {

    @Test
    public void testUserCanAddContact() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .firstName("Mike")
                .lastName("Smith")
                .phone("(981)134-31-12")
                .email("msmith@yahoo.com")
                .build();
        Response addContactResponse = contactClient.addContact(token.getAccess_token(), contact);
        assertEquals(addContactResponse.status(), HttpStatus.CREATED.value());

        ContactDTO createdContact = toPojo(addContactResponse, ContactDTO.class);
        assertEquals(contact.getFirstName(), createdContact.getFirstName());
        assertEquals(contact.getLastName(), createdContact.getLastName());
        assertEquals(contact.getEmail(), createdContact.getEmail());
        assertEquals(contact.getPhone(), createdContact.getPhone());
    }
}
