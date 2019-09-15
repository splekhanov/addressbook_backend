package com.addressbook.integration.tests.contact;

import com.addressbook.integration.base.BaseTest;
import com.addressbook.integration.model.contact.Contact;
import com.addressbook.utils.mapper.contact.ContactMapper;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class ContactServiceIntegrationTest extends BaseTest {

    @Autowired
    ContactMapper contactMapper;

    @Test(priority = 1)
    public void createNewContact() {

        Contact newContact = Contact.builder()
                .id(1L)
                .firstName("Brad")
                .lastName("Pitt")
                .phone("+79811543120")
                .email("bradpitt@yahoo.com")
                .build();

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(newContact)
                .auth()
                .oauth2(getAuthToken())
                .log().all()
                .when()
                .filter(new ErrorLoggingFilter())
                .post("http://localhost:" + randomPort + "/api/v1/contacts")
                .then()
                .statusCode(201)
                .log()
                .body();

        assertEquals(response.extract().as(Contact.class), newContact);
    }

    @Test(priority = 2)
    public void getContactById() {

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(authToken)
                .log().all()
                .when()
                .filter(new ErrorLoggingFilter())
                .get("http://localhost:" + randomPort + "/api/v1/contacts/1")
                .then()
                .statusCode(200)
                .log()
                .body();

        Contact expectedContact = new Contact (1L,"Brad","Pitt","+79811543120","bradpitt@yahoo.com");
        assertEquals(response.extract().as(Contact.class), expectedContact);
    }

    @Test(priority = 3)
    public void getAllContacts() {

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(authToken)
                .log().all()
                .when()
                .filter(new ErrorLoggingFilter())
                .get("http://localhost:" + randomPort + "/api/v1/contacts")
                .then()
                .statusCode(200)
                .log()
                .body();

        Contact expectedContact = new Contact (1L,"Brad","Pitt","+79811543120","bradpitt@yahoo.com");

        List<Contact> expected = Arrays.asList(expectedContact);
        List<Contact> actual = response.extract().jsonPath().getList("content", Contact.class);

        assertEquals(actual, expected);
    }
}
