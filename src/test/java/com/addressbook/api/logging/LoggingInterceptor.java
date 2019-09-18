package com.addressbook.api.logging;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingInterceptor implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

//    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
//        Request request = chain.request();
//
//        long t1 = System.nanoTime();
//        log.info(String.format("Sending request %s on %s%n%s",
//                request.url(), chain.connection(), request.headers()));
//
//        Response response = chain.proceed(request);
//
//        long t2 = System.nanoTime();
//        log.info(String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//        return response;
//    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info(String.format("Sending request %s on %s",
                requestTemplate.url(), requestTemplate.headers()));
    }
}
