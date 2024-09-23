package com.example.cbox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(CBoxApplication.class, args);
        log.info("Hello World!");
    }

}
