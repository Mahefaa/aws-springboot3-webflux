package com.amazonaws.webfluxapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxTestApplication {
    public static void main(String[] args) {
        var app = new SpringApplication(WebFluxTestApplication.class);
        app.run(args);
    }
}
