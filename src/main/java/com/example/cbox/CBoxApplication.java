package com.example.cbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication()
public class CBoxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CBoxApplication.class, args);
    }

}
