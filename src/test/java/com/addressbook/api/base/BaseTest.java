package com.addressbook.api.base;

import com.addressbook.api.clients.UserClient;
import com.google.gson.Gson;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.io.Reader;

@EqualsAndHashCode
@Data
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @LocalServerPort
    public int randomPort;

    public String url;
    public UserClient userClient;

    @BeforeAll
    public void init() {
        url = constructUrl();
        userClient = initUserClient();
    }

    public String constructUrl() {
        return "http://localhost:" + randomPort + "/api/v1";
    }

    public <T> T toPojo(Reader reader, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    //--------------CLIENTS---------------

    private UserClient initUserClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(UserClient.class))
                .logLevel(Logger.Level.FULL)
                .target(UserClient.class, getUrl());
    }
}
