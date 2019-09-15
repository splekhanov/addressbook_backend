package com.addressbook.integration.model.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private String authToken;
    private String tokenType;

}
