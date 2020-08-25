package com.cmed.health.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Configuration
public class CorsLocalConfig {

    // check https://spring.io/blog/2015/06/08/cors-support-in-spring-framework

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS","PATCH")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }

}
