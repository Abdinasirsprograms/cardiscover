package com.abdi.cardiscover;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@ComponentScan(basePackages = "com.abdi.cardiscover")
@EnableJpaRepositories
public class CarDiscover {

    

    public static void main(String[] args) {
        SpringApplication.run(CarDiscover.class, args);
    }

}