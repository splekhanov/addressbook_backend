package com.addressbook.repository.contact;

import com.addressbook.entity.contact.Contact;
import com.addressbook.entity.customer.Customer;
import com.addressbook.entity.security.User;
import com.addressbook.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ContactRepository extends BaseRepository<Contact, Long> {

    Page<Contact> findAllByCustomer(Customer customer, Pageable pageable);
}
