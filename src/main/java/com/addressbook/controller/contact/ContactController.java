package com.addressbook.controller.contact;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Contact", value = "ContactCommands", description = "Controller for Contact Commands")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @ApiOperation(value = "Get contact by ID")
    @GetMapping(value = "/contacts/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContactDTO> getAccount(@PathVariable("id") Long id) {
        return ok(contactService.getContact(id));
    }
}
