package com.platform.gateway.provider.client;

import com.platform.gateway.provider.fallback.AccessClientFallbackFactory;
import com.platform.gateway.provider.request.AccessRequestVo;
import com.platform.gateway.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sc-support-service", fallbackFactory = AccessClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface AccessClient {

	/**
	 * 限制检查
	 *
	 * @param
	 * request
	 * @return
	 */
	@RequestMapping(value = "/access/check", method = RequestMethod.POST)
	String checkAccessLimit(@RequestBody AccessRequestVo request);

	/**
	 * 增加访问量
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/access/increment", method = RequestMethod.POST)
	String increment(@RequestBody AccessRequestVo request);
}
