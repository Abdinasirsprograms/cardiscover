/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abdi.cardiscover;

/**
 *
 * @author Abdinasir
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class ResourceConfigs implements WebMvcConfigurer
{
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
    {
        "classpath:/META-INF/resources/",
        "classpath:/resources/",
        "classpath:/static/", 
        "classpath:/public/",
        "classpath:/custom/",
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**")
		.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
		.setCachePeriod(3000);
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("https://abdinasirnoor.com", "http://192.168.1.196:4200", "http://localhost:8080");
    }
};
