package com.mcakir.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignOauth2InterceptorDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignOauth2InterceptorDemoApplication.class, args);
	}

}
