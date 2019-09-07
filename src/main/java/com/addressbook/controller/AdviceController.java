package com.addressbook.controller;

import com.addressbook.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.addressbook.constants.Constants.BODY;
import static com.addressbook.constants.Constants.MESSAGE;

@Slf4j
@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleException(Exception ex) {
        return process(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException(NotFoundException ex) {
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleAccessDeniedException(AccessDeniedException ex) {
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Map<String, String> handleAuthException(BadCredentialsException ex) {
        Map<String, String> result = new HashMap<>();
        result.put(MESSAGE, ex.toString());
        result.put(BODY, "");
        log.warn("Failure authentication attempt");
        return result;
    }

    private Map<String, String> process(Exception ex, HttpStatus status) {
        Map<String, String> result = new HashMap<>();
        result.put(MESSAGE, ex.toString());
        result.put(BODY, ExceptionUtils.getStackTrace(ex));
        log.error(ex.getMessage(), ex);
        return result;
    }

}
