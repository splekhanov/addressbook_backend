package com.addressbook.service;

import com.addressbook.dto.security.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO createUser(UserDTO user);

//    UserDTO getUser(Long id);

//    void deleteContact(int id);

//    Page<UserDTO> getAllUsers(Pageable pageable);

    void deleteUserByUserName(String name);
}
