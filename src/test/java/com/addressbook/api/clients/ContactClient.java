package com.addressbook.api.clients;

import com.addressbook.dto.contact.ContactDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface ContactClient {

    @RequestLine("POST /contacts")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    Response addContact(@Param("token") String token, ContactDTO contactDTO);

    @RequestLine("GET /contacts/{id}")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    Response getContactById(@Param("token") String token, @Param("id") Long id);
}
