package com.addressbook.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class AdviceController {

//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public void handleException(Exception ex) {
//    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    private static class ErrorResponse {
        private String timestamp;
        private String status;
        private String error;
        private String message;
    }

    private ErrorResponse process(Exception ex, HttpStatus status) {
        ErrorResponse result = new ErrorResponse();
        result.setTimestamp(new Date().toString());
        result.setStatus(String.valueOf(status.value()));
        result.setError(String.valueOf(status.getReasonPhrase()));
        result.setMessage(ex.getMessage());
        log.error(ex.getMessage());
        return result;
    }
}
