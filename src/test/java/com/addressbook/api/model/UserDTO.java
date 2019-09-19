package com.addressbook.api.model;

import com.addressbook.dto.IdentifiedDTO;
import com.addressbook.dto.security.RoleDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@Builder
public class UserDTO implements IdentifiedDTO {

    private Long id;
    private String name;
    private String password;
    private boolean enabled;
    private List<RoleDTO> roles;
}
