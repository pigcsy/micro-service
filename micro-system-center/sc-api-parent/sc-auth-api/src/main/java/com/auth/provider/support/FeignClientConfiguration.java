package com.auth.provider.support;

import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.auth.provider.fallback.AuthorizationFallbackFactory;
import com.core.support.feign.DefaultFeignClientConfiguration;
import com.core.support.security.AuthorizationConverter;

@Configuration
@Import(value = {FeignClientsConfiguration.class,AuthorizationConverter.class})
public class FeignClientConfiguration extends DefaultFeignClientConfiguration {
	@Bean
	public AuthorizationFallbackFactory authorizationFallbackFactory(){
		return new AuthorizationFallbackFactory();
	}
}