package com.blogAPI.Blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Blog API Documentation", description = "Spring Boot Blog APP Rest API Documentation",
version = "V1.0",
contact = @Contact(name = "Vishal", email = "vishalmishra6604@gmail.com", url = ""),
license = @License(
        name = "Apache 2.0",
        url = ""

)),
externalDocs = @ExternalDocumentation(
        description = "Spring Boot Blog API Documentation",
        url = ""
))
public class BlogApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("Hello world");

    }


}
