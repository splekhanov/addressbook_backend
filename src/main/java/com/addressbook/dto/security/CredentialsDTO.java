package com.addressbook.dto.security;

import lombok.*;

@Getter
@Setter
@Builder
public class CredentialsDTO {

    private String username;
    private String password;

}
