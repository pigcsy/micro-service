package com.support.boot.config;

import com.core.support.security.TransmitAuthenticationProcessingConfiguration;
import com.core.support.web.MicroExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.support.manager", "com.cache.support.redis"})
@Import(value = {MicroExceptionHandler.class, TransmitAuthenticationProcessingConfiguration.class})
public class CommonConfig {

}
