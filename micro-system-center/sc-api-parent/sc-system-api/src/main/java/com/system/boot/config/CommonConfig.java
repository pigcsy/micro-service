package com.system.boot.config;

import com.core.support.security.TransmitAuthenticationProcessingConfiguration;
import com.core.support.web.MicroExceptionHandler;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.system.manager", "com.core.support.hystrix", "com.cache.support", "com.system.provider.fallback", "com.system.provider.support"})
@EnableFeignClients(basePackages = "com.system.provider.client")
@Import(value = {TransmitAuthenticationProcessingConfiguration.class, MicroExceptionHandler.class})
public class CommonConfig {

}