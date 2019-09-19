package com.addressbook.dto.security;

import com.addressbook.dto.IdentifiedDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements IdentifiedDTO {

    private static final long serialVersionUID = -1102167282001305540L;

    @ApiModelProperty(value = "The id", position = 1, hidden = true)
    private Long id;

    @ApiModelProperty(value = "User name", position = 2)
    private String name;

    @ApiModelProperty(value = "User password", position = 3)
    private String password;

    @ApiModelProperty(value = "User status", position = 4, hidden = true)
    private boolean enabled;

    @ApiModelProperty(value = "User roles", position = 5, hidden = true)
    private List<RoleDTO> roles;
}
