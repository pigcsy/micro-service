package com.system.provider.client;

import com.system.domain.DefaultRequestVo;
import com.system.domain.request.external.ExternalListRequestVo;
import com.system.domain.request.external.ExternalRequestVo;
import com.system.domain.response.external.ExternalListResponseVo;
import com.system.domain.response.external.ExternalResult;
import com.system.provider.fallback.SystemExternalClientFallbackFactory;
import com.system.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sc-system-service", fallbackFactory = SystemExternalClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface SystemExternalClient {

    @RequestMapping(value = "/external/list", method = RequestMethod.POST)
    ExternalListResponseVo list(@RequestBody ExternalListRequestVo request);

    @RequestMapping(value = "/external/detail", method = RequestMethod.POST)
    ExternalResult detail(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/external/edit", method = RequestMethod.POST)
    void edit(@RequestBody ExternalRequestVo request);

    @RequestMapping(value = "/external/delete", method = RequestMethod.POST)
    void delete(@RequestBody DefaultRequestVo request);
}
