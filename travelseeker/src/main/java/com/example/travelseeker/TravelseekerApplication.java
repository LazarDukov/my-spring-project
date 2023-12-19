package com.example.travelseeker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelseekerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelseekerApplication.class, args);
    }

}
