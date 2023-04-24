package com.example.ejercicio1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    public ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot laptops API REST",
                "Laptops apirest docs",
                "2.0",
                "http://www.google.com",
                new Contact("Leo","http://www.google.com","leomacrini15@gmail.com" ),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());    }
}
