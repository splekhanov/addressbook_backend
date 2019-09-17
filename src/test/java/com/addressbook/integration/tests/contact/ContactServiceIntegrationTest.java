package com.addressbook.integration.tests.contact;

import com.addressbook.integration.base.BaseTest;
import com.addressbook.integration.model.contact.Contact;
import com.addressbook.utils.mapper.contact.ContactMapper;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Arrays;
import java.util.List;

import static com.addressbook.integration.base.RequestSpecProvider.buildRequestSpec;
import static com.addressbook.integration.base.ResponseService.sendGetRequest;
import static com.addressbook.integration.base.ResponseService.sendPostRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContactServiceIntegrationTest extends BaseTest {

    @Autowired
    ContactMapper contactMapper;

    @Test
    public void createNewContact() {
        String url = resource("/contacts");
        String token = login(createNewUser("John", "123", "admin"));

        Contact newContact = Contact.builder()
                .firstName("Brad")
                .lastName("Pitt")
                .phone("+79811543120")
                .email("bradpitt@yahoo.com")
                .build();

        RequestSpecification requestSpec = buildRequestSpec(url, token);
        ValidatableResponse response = sendPostRequest(requestSpec, newContact);

        assertEquals(response.extract().statusCode(), 201);
        Contact actualContact = response.extract().as(Contact.class);
        actualContact.setId(null);
        assertEquals(actualContact, newContact);
    }

    @Test
    public void getContactById() {
        String urlCreate = resource("/contacts/");
        String token = login(createNewUser("Damien", "123", "admin"));

        Contact newContact = Contact.builder()
                .firstName("Bill")
                .lastName("Murray")
                .phone("+79811543120")
                .email("murray@yahoo.com")
                .build();

        RequestSpecification createNewContactSpec = buildRequestSpec(urlCreate, token);
        ValidatableResponse postResponse = sendPostRequest(createNewContactSpec, newContact);
        assertEquals(postResponse.extract().statusCode(), 201);
        Contact createdContact = postResponse.extract().as(Contact.class);

        String urlGet = resource("/contacts/" + createdContact.getId());
        RequestSpecification getContactRequestSpec = buildRequestSpec(urlGet, token);
        Contact actualContact = sendGetRequest(getContactRequestSpec).extract().as(Contact.class);
        assertEquals(actualContact, createdContact);
    }

    @Test
    public void getAllContacts() {
        String url = resource("/contacts/");
        String token = login(createNewUser("Fred", "123", "admin"));

        Contact newContact = Contact.builder()
                .firstName("Jack")
                .lastName("Nicholson")
                .phone("+79811543120")
                .email("nicholson@yahoo.com")
                .build();

        RequestSpecification createNewContactSpec = buildRequestSpec(url, token);
        ValidatableResponse postResponse = sendPostRequest(createNewContactSpec, newContact);
        assertEquals(postResponse.extract().statusCode(), 201);
        Contact createdContact = postResponse.extract().as(Contact.class);
        List<Contact> expectedContacts = Arrays.asList(createdContact);

        RequestSpecification getContactRequestSpec = buildRequestSpec(url, token);
        ValidatableResponse getAllResponse = sendGetRequest(getContactRequestSpec);

        assertEquals(getAllResponse.extract().statusCode(), 200);
        List<Contact> actualContacts = getAllResponse.extract().body().jsonPath().getList("content", Contact.class);
        assertEquals(expectedContacts, actualContacts);
    }
}
