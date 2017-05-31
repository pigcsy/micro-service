package com.eureka.boot;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
@EnableTurbine
@EnableHystrixDashboard
@ComponentScan(basePackages="com.eureka.provider")
public class MonitorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MonitorApplication.class, args);
	}

}
