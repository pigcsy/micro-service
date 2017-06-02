package com.safty.boot.config;

import com.core.support.security.TransmitAuthenticationProcessingConfiguration;
import com.core.support.web.MicroExceptionHandler;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.safty.manager", "com.core.support.hystrix", "com.safty.provider.support"})
@EnableFeignClients(basePackages = "com.safty.provider.client")
@Import(value = {MicroExceptionHandler.class, TransmitAuthenticationProcessingConfiguration.class})
public class CommonConfig {

}