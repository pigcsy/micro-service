package com.safty.boot.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.core.support.security.TransmitAuthenticationProcessingConfiguration;
import com.core.support.web.MicroExceptionHandler;

@Configuration
@ComponentScan(basePackages = { "com.safty.manager", "com.core.support.hystrix","com.safty.provider.support"})
@EnableFeignClients(basePackages = "com.safty.provider.client")
@Import(value = {MicroExceptionHandler.class,TransmitAuthenticationProcessingConfiguration.class })
public class CommonConfig {

}