package com.mcakir.demo.service;

import com.mcakir.demo.config.OAuth2FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "${feign-client.api.name}",
        url = "${feign-client.api.url}",
        configuration = OAuth2FeignConfig.class)
public interface ApiClient {

    @PostMapping(value = "conversation")
    void sayHello();

}