package com.addressbook.controller.contact;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ContactController {

    private final ContactService contactService;

    @Autowired
    ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping(value = "/contacts/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContactDTO> getAccount(@PathVariable("id") Long id) {
        return ok(contactService.getContact(id));
    }
}
