package com.system.provider.client;

import com.system.domain.DefaultRequestVo;
import com.system.domain.request.role.RoleListRequestVo;
import com.system.domain.request.role.RoleRequestVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.domain.response.role.RoleListResponseVo;
import com.system.domain.response.role.RoleResourcesResponseVo;
import com.system.provider.fallback.SystemRoleClientFallbackFactory;
import com.system.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sc-system-service", fallbackFactory = SystemRoleClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface SystemRoleClient {

    @RequestMapping(value = "/role/list", method = RequestMethod.POST)
    RoleListResponseVo list(@RequestBody RoleListRequestVo request);

    @RequestMapping(value = "/role/detail", method = RequestMethod.POST)
    ResourcesDetailResponseVo detail(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/role/edit", method = RequestMethod.POST)
    void edit(@RequestBody RoleRequestVo request);

    @RequestMapping(value = "/role/delete", method = RequestMethod.POST)
    void delete(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/role/resource-list", method = RequestMethod.POST)
    RoleResourcesResponseVo resourceList(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/role/resource-edit", method = RequestMethod.POST)
    void resourceEdit(@RequestBody RoleListRequestVo request);
}
