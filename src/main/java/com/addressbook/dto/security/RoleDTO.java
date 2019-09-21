package com.addressbook.dto.security;

import com.addressbook.dto.IdentifiedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements IdentifiedDTO {

    private static final long serialVersionUID = -8839292294470944560L;

    private Long id;
    private String name;
}
