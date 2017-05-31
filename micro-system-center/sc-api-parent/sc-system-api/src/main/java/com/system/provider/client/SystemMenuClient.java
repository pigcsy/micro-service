package com.system.provider.client;

import com.system.domain.response.menu.MenuResponseVo;
import com.system.provider.fallback.SystemMenuClientFallbackFactory;
import com.system.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sc-system-service", fallbackFactory = SystemMenuClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface SystemMenuClient {

    @RequestMapping(value = "/menu/list", method = RequestMethod.POST)
    MenuResponseVo menu();
}
