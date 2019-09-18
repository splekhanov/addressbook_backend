package com.addressbook.api.model;

import lombok.Data;

@Data
public class TokenDTO {

    private String access_token;
    private String error_description;
    private String error;
}