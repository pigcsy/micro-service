package com.safty.provider.support;

import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.core.support.feign.DefaultFeignClientConfiguration;
import com.safty.provider.fallback.RiskClientFallbackFactory;

@Configuration
@Import(FeignClientsConfiguration.class)
public class FeignClientConfiguration extends DefaultFeignClientConfiguration {
	@Bean
	public RiskClientFallbackFactory carClientFallbackFactory(){
		return new RiskClientFallbackFactory();
	}
}