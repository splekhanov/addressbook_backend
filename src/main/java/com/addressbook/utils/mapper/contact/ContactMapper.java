package com.addressbook.utils.mapper.contact;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.entity.contact.Contact;
import com.addressbook.utils.mapper.EntityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper implements EntityMapper<Contact, ContactDTO> {

    @Override
    public ContactDTO toDto(Contact source) {
        if (source == null) {
            return null;
        }
        ContactDTO target = new ContactDTO();
        BeanUtils.copyProperties(source, target, "customer");
        return target;
    }

    @Override
    public Contact toEntity(ContactDTO source) {
        if (source == null) {
            return null;
        }
        Contact target = new Contact();
        BeanUtils.copyProperties(source, target, "customer");
        return target;
    }

}
