package com.addressbook.api.clients;

import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.dto.security.UserDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserClient {

    @RequestLine("POST /registration")
    @Headers({"Content-Type: application/json"})
    Response register(UserDTO userDTO);

    @RequestLine("POST /login")
    @Headers({"Content-Type: application/json"})
    Response login(CredentialsDTO credentialsDTO);

    @RequestLine("DELETE /users/{id}")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    Response deleteUserById(@Param("token") String token, @Param("id") Long id);

    @RequestLine("PUT /users/{id}/undelete")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    Response undeleteUser(@Param("token") String token, @Param("id") Long id);

    @RequestLine("GET /users/{id}")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    Response getUserById(@Param("token") String token, @Param("id") Long id);

    @RequestLine("GET /users?name={name}")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    Response getUserByName(@Param("token") String token, @Param("name") String name);
}
