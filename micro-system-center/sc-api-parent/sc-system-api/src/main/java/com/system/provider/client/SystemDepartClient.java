package com.system.provider.client;

import com.system.domain.DefaultRequestVo;
import com.system.domain.request.depart.DepartListRequestVo;
import com.system.domain.request.depart.DepartRequestVo;
import com.system.domain.request.resourse.ResourcesRequestVo;
import com.system.domain.response.depart.DepartListResponseVo;
import com.system.domain.response.depart.DepartResultVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.provider.fallback.SystemDepartClientFallbackFactory;
import com.system.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "sc-system-service", fallbackFactory = SystemDepartClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface SystemDepartClient {

    @RequestMapping(value = "/depart/list", method = RequestMethod.POST)
    DepartListResponseVo list(@RequestBody DepartListRequestVo request);

    @RequestMapping(value = "/depart/detail", method = RequestMethod.POST)
    DepartResultVo detail(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/depart/edit", method = RequestMethod.POST)
    void edit(@RequestBody DepartRequestVo request);

    @RequestMapping(value = "/depart/search", method = RequestMethod.POST)
    ResourcesDetailResponseVo search(@RequestBody ResourcesRequestVo request);

    @RequestMapping(value = "/depart/delete", method = RequestMethod.POST)
    void delete(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/depart/edit-list", method = RequestMethod.POST)
    List<DepartResultVo> editList(@RequestBody DepartRequestVo request);
}
