package com.cmed.health.app.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//TODO: organize component scanor

@SpringBootApplication
@ComponentScan({"com.cmed.health.core.service"})
@EntityScan("com.cmed.health.core.entity")
@EnableJpaRepositories("com.cmed.health.core.repository")
@ComponentScan(basePackages = "com.cmed.health.app")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
