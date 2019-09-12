package com.addressbook.service;

import com.addressbook.dto.contact.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    ContactDTO addContact(ContactDTO contact);

    ContactDTO getContact(Long id);

//    void deleteContact(int id);
//
    Page<ContactDTO> getAllContacts(Pageable pageable);
}
