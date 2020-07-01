package com.ck.petadopt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication( scanBasePackages = "com.ck")
@EntityScan( basePackages = "com.ck.data")
@SpringBootConfiguration
public class PetAdoptApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PetAdoptApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PetAdoptApplication.class);
    }
}

