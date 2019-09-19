package com.addressbook.service;

import com.addressbook.dto.security.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO createUser(UserDTO user);

//    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO getUserById(Long id);

    UserDTO getUserByName(String name);

    void deleteUserById(Long id);
}
