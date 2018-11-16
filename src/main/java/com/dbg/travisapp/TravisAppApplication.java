package com.dbg.travisapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@SpringBootApplication
public class TravisAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravisAppApplication.class, args);
    }

}
