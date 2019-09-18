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
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyTest extends BaseTest {

    @Test
    public void justTest() throws IOException {

        String url = "http://localhost:" + randomPort + "/api/v1/login";

        UserClient userClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(new LoggingInterceptor())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(UserClient.class, url);

        CredentialsDTO credentials = new CredentialsDTO("flash","1234");
        Response response = userClient.login(credentials);

        Gson gson = new Gson();
        TokenDTO token = gson.fromJson(response.body().asReader(), TokenDTO.class);

        System.out.println(token);
    }
}
