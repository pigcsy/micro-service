package com.auth.boot;


import com.core.base.DefaultSpringbootInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableWebSecurity
@RestController
@EnableHystrix
@ComponentScan(value = {"com.auth.boot.config"})
public class AuthApplication extends DefaultSpringbootInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }


}
