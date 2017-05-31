package com.platform.gateway.boot;

import com.core.base.DefaultSpringbootInitializer;
import com.core.support.web.MicroExceptionHandler;
import com.platform.gateway.filter.OptionsIgnoreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableZuulProxy
@EnableEurekaClient
@ComponentScan(value = { "com.platform.gateway.filter","com.platform.gateway.security",
"com.cache.support.redis","com.platform.gateway.controller", "com.platform.gateway.provider.support", "com.core.support.hystrix"})
@EnableFeignClients(basePackages = "com.platform.gateway.provider.client")
@Import(MicroExceptionHandler.class)
public class SystemGatewayApplication extends DefaultSpringbootInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SystemGatewayApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean registrationTransmitAuthenticationProcessingFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
				new OptionsIgnoreFilter());// ServletName默认值为首字母小写，即myServlet
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}


}
