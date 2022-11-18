package com.mcakir.demo;

import com.mcakir.demo.service.ApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CmdRunner implements CommandLineRunner {

    private final ApiClient apiClient;

    @Override
    public void run(String... args) throws Exception {

        apiClient.sayHello();

    }
}
