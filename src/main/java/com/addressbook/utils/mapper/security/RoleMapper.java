package com.addressbook.utils.mapper.security;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.dto.security.RoleDTO;
import com.addressbook.entity.contact.Contact;
import com.addressbook.entity.security.Role;
import com.addressbook.utils.mapper.EntityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements EntityMapper<Role, RoleDTO> {

    @Override
    public RoleDTO toDto(Role source) {
        if (source == null) {
            return null;
        }
        RoleDTO target = new RoleDTO();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    @Override
    public Role toEntity(RoleDTO source) {
        if (source == null) {
            return null;
        }
        Role target = new Role();
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
