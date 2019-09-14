package com.addressbook.service.impl;

import com.addressbook.dto.security.RoleDTO;
import com.addressbook.dto.security.UserDTO;
import com.addressbook.entity.security.Role;
import com.addressbook.entity.security.User;
import com.addressbook.exceptions.AdminDeletionForbiddenException;
import com.addressbook.exceptions.NotFoundException;
import com.addressbook.repository.security.RoleRepository;
import com.addressbook.repository.security.UserRepository;
import com.addressbook.service.UserService;
import com.addressbook.utils.mapper.security.RoleMapper;
import com.addressbook.utils.mapper.security.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           RoleMapper roleMapper,
                           PasswordEncoder bCryptPasswordEncoder,
                           RoleRepository roleReposiroty) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleReposiroty;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByName(userDTO.getName()).isPresent()) {
            throw new RuntimeException(String.format("User with name '%s' already exists!", userDTO.getName()));
        }
        Role userRole = roleRepository.findByName("admin").orElse(new Role("admin"));
        List<RoleDTO> userRolesDTO = new ArrayList<>();
        userRolesDTO.add(roleMapper.toDto(userRole));
        userDTO.setRoles(userRolesDTO);
        User entity = userMapper.toEntity(userDTO);
        entity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        entity.setEnabled(true);
        User result = userRepository.saveAndFlush(entity);
        return userMapper.toDto(result);
    }

    @Override
    public void deleteUserByUserName(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new RuntimeException(String.format("User with name '%s' not found!", userName)));
        if (user.getRoles().stream().anyMatch(role -> "admin".equals(role.getName()))) {
            throw new AdminDeletionForbiddenException("You can not delete admin!");
        }
        if (!user.isEnabled()) {
            throw new NotFoundException("User has already been deleted");
        } else {
            user.setEnabled(false);
            userRepository.save(user);
        }
    }
}
