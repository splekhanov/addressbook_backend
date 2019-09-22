package com.addressbook.controller.contact;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Contacts", value = "ContactCommands", description = "Controller for Contact Commands")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @ApiOperation(value = "Get contact by ID", authorizations = @Authorization(value="Authorization"))
    @GetMapping(value = "/contacts/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContactDTO> getContact(@PathVariable("id") Long id) {
        return ok(contactService.getContact(id));
    }

    @ApiOperation(value = "Add new contact", authorizations = @Authorization(value="Authorization"))
    @PostMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContactDTO> addContact(@RequestBody ContactDTO contact) {
        ContactDTO contactDTO = contactService.addContact(contact);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contactDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(contactDTO);
    }

    @ApiOperation(value = "Edit existing contact", authorizations = @Authorization(value="Authorization"))
    @PutMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContactDTO> editContact(@RequestBody ContactDTO contact) {
        ContactDTO contactDTO = contactService.editContact(contact);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contactDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(contactDTO);
    }

    @ApiOperation(value = "Get all user contacts", authorizations = @Authorization(value="Authorization"))
    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ok(contactService.getAllContacts());
    }

    @ApiOperation(value = "Delete contact", authorizations = @Authorization(value="Authorization"))
    @DeleteMapping(value = "/contacts/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
