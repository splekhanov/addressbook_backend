package com.addressbook.api;

import com.addressbook.api.logging.LoggingInterceptor;
import com.addressbook.api.model.TokenDTO;
import com.addressbook.dto.security.CredentialsDTO;
import com.addressbook.integration.base.BaseTest;
import com.google.gson.Gson;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
public class MyTest extends BaseTest {

    @Test
    public void justTest() throws IOException {

        UserClient userClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(new LoggingInterceptor())
                .target(UserClient.class, getUrl());

        CredentialsDTO credentials = new CredentialsDTO("flash","1234");
        Response response = userClient.login(credentials);

        Gson gson = new Gson();
        TokenDTO token = gson.fromJson(response.body().asReader(), TokenDTO.class);
        System.out.println(token);
    }
}
