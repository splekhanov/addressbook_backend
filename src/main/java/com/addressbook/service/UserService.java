package com.addressbook.service;

import com.addressbook.dto.security.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void createUser(UserDTO user);

//    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO getUserById(Long id);

    UserDTO getUserByName(String name);

    void undeleteUser(Long id);

    void deleteUserById(Long id);
}
