package com.addressbook.utils.mapper.security;

import com.addressbook.dto.security.RoleDTO;
import com.addressbook.dto.security.UserDTO;
import com.addressbook.entity.security.Role;
import com.addressbook.entity.security.User;
import com.addressbook.utils.mapper.EntityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements EntityMapper<User, UserDTO> {

    @Override
    public UserDTO toDto(User source) {
        if (source == null) {
            return null;
        }
        UserDTO target = new UserDTO();
        BeanUtils.copyProperties(source, target, "password", "roles");
        List<RoleDTO> roles = source.getRoles().stream()
                .map(it -> {
                    RoleDTO role = new RoleDTO();
                    BeanUtils.copyProperties(it, role);
                    return role;
                })
                .collect(Collectors.toList());
        target.setRoles(roles);
        return target;
    }

    @Override
    public User toEntity(UserDTO source) {
        if (source == null) {
            return null;
        }
        User target = new User();
        BeanUtils.copyProperties(source, target, "roles");
        List<Role> roles = source.getRoles()
                .stream()
                .map(it -> {
                    Role role = new Role();
                    BeanUtils.copyProperties(it, role);
                    return role;
                })
                .collect(Collectors.toList());
        target.setRoles(roles);
        return target;
    }
}
