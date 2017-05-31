package com.system.provider.client;

import com.system.domain.DefaultRequestVo;
import com.system.domain.request.resourse.ResourcesListRequestVo;
import com.system.domain.request.resourse.ResourcesRequestVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.domain.response.resource.ResourcesListResponseVo;
import com.system.provider.fallback.SystemResourceClientFallbackFactory;
import com.system.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "sc-system-service", fallbackFactory = SystemResourceClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface SystemResourceClient {

    @RequestMapping(value = "/resource/list", method = RequestMethod.POST)
    ResourcesListResponseVo list(@RequestBody ResourcesListRequestVo request);

    @RequestMapping(value = "/resource/detail", method = RequestMethod.POST)
    ResourcesDetailResponseVo detail(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/resource/edit", method = RequestMethod.POST)
    void edit(@RequestBody ResourcesRequestVo request);

    @RequestMapping(value = "/resource/search", method = RequestMethod.POST)
    ResourcesDetailResponseVo search(@RequestBody ResourcesRequestVo request);

    @RequestMapping(value = "/resource/delete", method = RequestMethod.POST)
    void delete(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/resource/role-edit", method = RequestMethod.POST)
    List<ResourcesDetailResponseVo> editList(@RequestBody ResourcesRequestVo request);
}
