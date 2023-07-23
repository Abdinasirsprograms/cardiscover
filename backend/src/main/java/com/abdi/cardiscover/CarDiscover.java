package com.abdi.cardiscover;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ComponentScan(basePackages = "com.abdi.cardiscover")
@EnableJpaRepositories
@EnableAutoConfiguration
public class CarDiscover extends SpringBootServletInitializer {
    
    
    
    public static void main(String[] args) {
        SpringApplication.run(CarDiscover.class, args);
    }

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CarDiscover.class);
    }

}