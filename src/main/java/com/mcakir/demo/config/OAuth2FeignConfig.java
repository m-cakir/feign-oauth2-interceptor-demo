package com.mcakir.demo.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

@Slf4j
@RequiredArgsConstructor
public class OAuth2FeignConfig {

    public static final String CLIENT_REGISTRATION_ID = "api";

    private final OAuth2ClientProvider oauth2ClientProvider;

    @Bean
    public RequestInterceptor oauth2Interceptor() {
        return (requestTemplate) -> {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, oauth2ClientProvider.getAuthenticationToken(CLIENT_REGISTRATION_ID));
        };
    }
}
