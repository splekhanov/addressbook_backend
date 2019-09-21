package com.addressbook.service;

import com.addressbook.dto.contact.ContactDTO;

import java.util.List;

public interface ContactService {

    ContactDTO addContact(ContactDTO contact);

    ContactDTO getContact(Long id);

    ContactDTO editContact(ContactDTO contact);

    void deleteContact(Long id);

    List<ContactDTO> getAllContacts();
}
