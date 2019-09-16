package com.addressbook.dto.security;

import com.addressbook.dto.IdentifiedDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class UserDTO implements IdentifiedDTO {

    private static final long serialVersionUID = -1102167282001305540L;

    @ApiModelProperty(hidden = true)
    private Long id;

    private String name;

    private String password;

    @ApiModelProperty(hidden = true)
    private boolean enabled;

    @ApiModelProperty(hidden = true)
    private List<RoleDTO> roles;
}
