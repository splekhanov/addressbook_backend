package com.addressbook.service.impl;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.entity.contact.Contact;
import com.addressbook.entity.security.User;
import com.addressbook.exceptions.ContactAlreadyExistsException;
import com.addressbook.exceptions.ResourceNotFoundException;
import com.addressbook.repository.contact.ContactRepository;
import com.addressbook.repository.security.UserRepository;
import com.addressbook.service.ContactService;
import com.addressbook.utils.UserUtils;
import com.addressbook.utils.mapper.contact.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Contact contact = findContactById(id);
        return contactMapper.toDto(contact);
    }

    @Override
    public ContactDTO addContact(ContactDTO contactDTO) {
        contactRepository.findContactByFirstName(contactDTO.getFirstName()).ifPresent(e -> {
            throw new ContactAlreadyExistsException("Contact with name '" + e.getFirstName() + "' is already exists");
        });
        return saveContactInRepository(contactDTO);
    }

    @Override
    public void deleteContact(Long id) {
        findContactById(id);
        contactRepository.deleteById(id);
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        User currentUser = currentUser();
        return contactRepository.findAllByUser(currentUser).stream().map(contactMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ContactDTO editContact(ContactDTO contactDTO) {
        contactRepository.findById(contactDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact with id '" + contactDTO.getId() + "' not found"));
        return saveContactInRepository(contactDTO);
    }

    private Contact findContactById(Long id){
        User currentUser = currentUser();
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact == null || !contact.getUser().equals(currentUser)) {
            throw new ResourceNotFoundException("No contact with id " + id + " was found");
        }
        return contact;
    }

    private ContactDTO saveContactInRepository(ContactDTO contactDTO) {
        User currentUser = currentUser();
        Contact entity = contactMapper.toEntity(contactDTO);
        entity.setUser(currentUser);
        Contact result = contactRepository.saveAndFlush(entity);
        return contactMapper.toDto(result);
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
