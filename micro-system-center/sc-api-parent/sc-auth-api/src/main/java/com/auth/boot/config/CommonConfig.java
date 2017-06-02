package com.auth.boot.config;

import com.core.support.web.MicroExceptionHandler;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = {"com.auth.security", "com.core.support.hystrix", "com.cache.support.redis", "com.auth.provider.support"})
@EnableFeignClients(basePackages = {"com.auth.provider.client"})
@Import(MicroExceptionHandler.class)
public class CommonConfig {

}