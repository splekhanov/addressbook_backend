package com.addressbook.api;

import com.addressbook.dto.security.CredentialsDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface UserClient {

    @RequestLine("POST /login")
    @Headers({"Content-Type: application/json"})
    Response login(CredentialsDTO credentialsDTO);
}
