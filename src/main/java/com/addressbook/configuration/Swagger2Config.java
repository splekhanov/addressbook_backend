package com.addressbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@PropertySource("classpath:swagger.properties")
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.addressbook.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo())
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()));
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Addressbook API Service")
                .description("Contacts management REST API")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/v1.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("test-app-client-id")
                .clientSecret("test-app-client-secret")
                .realm("test-app-realm")
                .appName("test-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

//    @Bean(name = "swagger")
//    public Docket swagger() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        val securityReferences = newArrayList(
//                new SecurityReference(AUTHORIZATION, authorizationScopes)
//        );
//        val securityContext = SecurityContext.builder()
//                .securityReferences(securityReferences)
//                .forPaths(PathSelectors.any())
//                .build();
//        val apiKeys = new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors
//                    .basePackage("com.addressbook.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                    .apiInfo(apiEndPointsInfo())
//                .enable(true)
//                .securitySchemes(newArrayList(apiKeys))
//                .securityContexts(newArrayList(securityContext));
//    }
//
//    @Bean
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
//        builder
//                .serializers(new JsonSerializer<LocalDateTime>() {
//                    @Override
//                    public Class<LocalDateTime> handledType() {
//                        return LocalDateTime.class;
//                    }
//                    @Override
//                    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
//                                          SerializerProvider serializerProvider) throws IOException {
//                        jsonGenerator.writeString(formatter.format(localDateTime));
//                    }
//                })
//                .deserializers(new JsonDeserializer<LocalDateTime>() {
//                    @Override
//                    public Class<LocalDateTime> handledType() {
//                        return LocalDateTime.class;
//                    }
//                    @Override
//                    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
//                            throws IOException, JsonProcessingException {
//                        return LocalDateTime.parse(p.getValueAsString(), formatter);
//                    }
//                });
//        return builder.build();
//    }
}
