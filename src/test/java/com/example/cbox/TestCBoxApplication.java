package com.example.cbox;

import org.springframework.boot.SpringApplication;

public class TestCBoxApplication {

    public static void main(String[] args) {
        SpringApplication.from(CBoxApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
