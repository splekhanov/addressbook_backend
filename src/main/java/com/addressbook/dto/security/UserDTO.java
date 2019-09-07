package com.addressbook.dto.security;

import com.addressbook.dto.IdentifiedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class UserDTO implements IdentifiedDTO {

    private static final long serialVersionUID = -1102167282001305540L;

    private Long id;
    private String name;
    private String password;
    private List<RoleDTO> roles;
}
