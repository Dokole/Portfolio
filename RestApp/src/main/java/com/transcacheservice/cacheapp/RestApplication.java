package com.transcacheservice.cacheapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RestApplication.class, args);
    }

}
