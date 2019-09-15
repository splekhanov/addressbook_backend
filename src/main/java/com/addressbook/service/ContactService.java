package com.addressbook.service;

import com.addressbook.dto.contact.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    ContactDTO addContact(ContactDTO contact);

    ContactDTO getContact(Long id);

    ContactDTO editContact(ContactDTO contact);

    void deleteContact(Long id);

    Page<ContactDTO> getAllContacts(Pageable pageable);
}
