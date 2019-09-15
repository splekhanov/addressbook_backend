package com.addressbook.repository.contact;

import com.addressbook.entity.contact.Contact;
import com.addressbook.entity.security.User;
import com.addressbook.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ContactRepository extends BaseRepository<Contact, Long> {

    Page<Contact> findAllByUser(User user, Pageable pageable);

    Optional<Contact> findContactByFirstName(String firstName);

}
