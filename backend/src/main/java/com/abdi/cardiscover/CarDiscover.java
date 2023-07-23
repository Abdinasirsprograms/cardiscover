package com.abdi.cardiscover;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
@ComponentScan(basePackages = "com.abdi.cardiscover")
@EnableJpaRepositories
@EnableAutoConfiguration
@CrossOrigin(origins = {"http://192.168.1.196:4200", "http://localhost:8080"})

public class CarDiscover extends SpringBootServletInitializer {

    

    public static void main(String[] args) {
        SpringApplication.run(CarDiscover.class, args);
    }

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CarDiscover.class);
    }
}