package com.addressbook.api.logging;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String newLine = System.getProperty("line.separator");

        log.info(String.format(
                newLine +
                newLine + "&#x09" +
                        "Sending request: " +
                newLine +
                        "URL: " + requestTemplate.url() +
                newLine +
                        "Headers: %s" +
                newLine,
                requestTemplate.headers()));
    }
}
