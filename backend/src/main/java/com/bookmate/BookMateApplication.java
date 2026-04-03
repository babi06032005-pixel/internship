package com.bookmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BookMateApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookMateApplication.class, args);
    }
}
