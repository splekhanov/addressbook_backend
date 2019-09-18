package com.addressbook.api;

import com.addressbook.dto.security.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResource {

    UserDTO userDTO;
}
