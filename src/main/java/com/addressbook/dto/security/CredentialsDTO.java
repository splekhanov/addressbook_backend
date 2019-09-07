package com.addressbook.dto.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CredentialsDTO {

    private String username;
    private String password;

}
