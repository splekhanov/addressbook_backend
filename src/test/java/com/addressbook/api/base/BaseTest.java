package com.addressbook.api.base;

import com.addressbook.api.clients.ContactClient;
import com.addressbook.api.clients.UserClient;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.google.gson.Gson;
import feign.Feign;
import feign.Logger;
import feign.Response;
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

import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

@EqualsAndHashCode
@Data
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @LocalServerPort
    public int randomPort;

    public static String url;
    public static UserClient userClient;
    public static ContactClient contactClient;

    @BeforeAll
    public void init() {
        url = constructUrl();
        userClient = createClient(UserClient.class);
        contactClient = createClient(ContactClient.class);
    }

    public String constructUrl() {
        return "http://localhost:" + randomPort + "/api/v1";
    }

    public static <T> T toPojo(Response response, Class<T> type) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(response.body().asReader(), type);
    }

    public String generateRandomUsername(){
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return fakeValuesService.regexify("[a-z1-9]{10}");
    }

    public Long generateRandomUserId(){
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return Long.parseLong(fakeValuesService.regexify("[1-9]{10}"));
    }

    private <T> T createClient(Class<T> type){
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.FULL)
                .target(type, url);
    }
}
