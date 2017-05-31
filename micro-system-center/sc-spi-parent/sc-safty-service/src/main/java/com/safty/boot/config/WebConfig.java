package com.safty.boot.config;

import com.core.support.web.DefaultWebConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(value = {"com.safty.controller"})
public class WebConfig extends DefaultWebConfig {


}