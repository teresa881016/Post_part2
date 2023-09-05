package com.example.postpart2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostPart2Application {

    public static void main(String[] args) {
        SpringApplication.run(PostPart2Application.class, args);
    }

}
