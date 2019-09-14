package com.addressbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AdminDeletionForbiddenException extends RuntimeException {

    public AdminDeletionForbiddenException(String message) {
        super(message);
    }
}
