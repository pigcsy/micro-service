package com.safty.provider.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.safty.domain.request.RiskRequest;
import com.safty.domain.response.RiskResponse;
import com.safty.provider.fallback.RiskClientFallbackFactory;
import com.safty.provider.support.FeignClientConfiguration;

@FeignClient(name = "dp-risk-service", fallbackFactory = RiskClientFallbackFactory.class , configuration = FeignClientConfiguration.class)
public interface RiskClient {


	@RequestMapping(value="/risk/data/datagather",method=RequestMethod.POST)
	RiskResponse riskDataTrade(RiskRequest request);

}
