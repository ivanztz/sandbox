package com.iz.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ObjectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectServiceApplication.class, args);
    }

}
