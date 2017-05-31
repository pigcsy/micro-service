package com.safty.boot.config;

import com.core.support.security.TransmitAuthenticationProcessingConfiguration;
import com.core.support.web.MicroExceptionHandler;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.safty.service", "com.cache.support.redis","com.core.support.hystrix"})
@Import(value = { MicroExceptionHandler.class,TransmitAuthenticationProcessingConfiguration.class} )
@EnableFeignClients(basePackages = "com.safty.provider")
public class CommonConfig {

}