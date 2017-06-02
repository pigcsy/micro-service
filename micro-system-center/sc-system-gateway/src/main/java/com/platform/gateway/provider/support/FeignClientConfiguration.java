package com.platform.gateway.provider.support;

import com.core.support.feign.DefaultFeignClientConfiguration;
import com.platform.gateway.provider.fallback.AccessClientFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientsConfiguration.class)
public class FeignClientConfiguration extends DefaultFeignClientConfiguration {
    @Bean
    public AccessClientFallbackFactory accessClientFallbackFactory() {
        return new AccessClientFallbackFactory();
    }
}
