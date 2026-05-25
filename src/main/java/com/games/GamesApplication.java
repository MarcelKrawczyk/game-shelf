package com.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamesApplication {
    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
        System.out.println("\nApp: http://localhost:8080");
        System.out.println("REST API: http://localhost:8080/data");
        System.out.println("DB Console: http://localhost:8080/h2-console\n");
    }
}