package com.my.odos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OdosApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdosApplication.class, args);
    }

}
