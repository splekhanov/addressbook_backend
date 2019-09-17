package com.addressbook.dto.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CredentialsDTO {

    @ApiModelProperty(value = "User name", position = 1)
    private String username;

    @ApiModelProperty(value = "User password", position = 2)
    private String password;

}
