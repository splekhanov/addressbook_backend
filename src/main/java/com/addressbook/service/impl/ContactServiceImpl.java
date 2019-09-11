package com.addressbook.service.impl;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.entity.contact.Contact;
import com.addressbook.entity.security.User;
import com.addressbook.exceptions.NotFoundException;
import com.addressbook.repository.contact.ContactRepository;
import com.addressbook.repository.security.UserRepository;
import com.addressbook.service.ContactService;
import com.addressbook.utils.UserUtils;
import com.addressbook.utils.mapper.contact.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
        private final UserRepository userRepository;
        private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactDTO getContact(Long id) {
        User currentUser = currentUser();
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact == null || !contact.getCustomer().equals(currentUser.getCustomer())) {
            throw new NotFoundException();
        }
        return contactMapper.toDto(contact);
    }

    private User currentUser() {
        Optional<Long> userId = UserUtils.getCurrentUserId();
        if (!userId.isPresent()) {
            throw new RuntimeException("User not authenticated");
        }
        if (!userRepository.existsById(userId.orElse(null))) {
            throw new RuntimeException("User not found");
        }
        return userRepository.findById(userId.orElse(null)).orElse(null);
    }
}
