package com.addressbook.integration.tests.contact;

import com.addressbook.integration.base.BaseTest;
import com.addressbook.integration.model.contact.Contact;
import com.addressbook.utils.mapper.contact.ContactMapper;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.addressbook.integration.base.RequestSpecProvider.buildRequestSpec;
import static com.addressbook.integration.base.ResponseService.sendGetRequest;
import static com.addressbook.integration.base.ResponseService.sendPostRequest;
import static org.testng.Assert.assertEquals;


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

//    @Test
//    public void getAllContacts() {
//        String token = login(createNewUser("Fred", "123", "admin"));
//        ValidatableResponse response = given()
//                .contentType(ContentType.JSON)
//                .auth()
//                .oauth2(token)
//                .log().all()
//                .when()
//                .filter(new ErrorLoggingFilter())
//                .get("http://localhost:" + randomPort + "/api/v1/contacts")
//                .then()
//                .statusCode(200)
//                .log()
//                .body();
//
//        Contact expectedContact = new Contact (1L,"Brad","Pitt","+79811543120","bradpitt@yahoo.com");
//
//        List<Contact> expected = Arrays.asList(expectedContact);
//        List<Contact> actual = response.extract().jsonPath().getList("content", Contact.class);
//
//        assertEquals(actual, expected);
//    }
}
