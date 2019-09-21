package com.addressbook.api.model;

import lombok.Data;

@Data
public class ErrorDetails {

    String timestamp;
    int status;
    String error;
    String message;
    String path;

}
