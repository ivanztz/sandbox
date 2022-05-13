package com.iz.sandbox;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ObjectEventsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ObjectEventsApplication.class, args);
    }
}
