package com.system.provider.support;

import com.core.support.feign.DefaultFeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientsConfiguration.class)
public class FeignClientConfiguration extends DefaultFeignClientConfiguration {

}