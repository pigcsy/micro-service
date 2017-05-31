package com.system.provider.client;

import com.system.domain.DefaultRequestVo;
import com.system.domain.request.user.UserListRequestVo;
import com.system.domain.request.user.UserRequestVo;
import com.system.domain.response.user.UserListResponseVo;
import com.system.domain.response.user.UserResult;
import com.system.domain.response.user.UserRoleResponseVo;
import com.system.provider.fallback.SystemUserClientFallbackFactory;
import com.system.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "sc-system-service", fallbackFactory = SystemUserClientFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface SystemUserClient {

    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    UserListResponseVo list(@RequestBody UserListRequestVo request);

    @RequestMapping(value = "/user/detail", method = RequestMethod.POST)
    UserResult detail(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    void editOrInsertUser(@RequestBody UserRequestVo request);

    @RequestMapping(value = "/user/disable", method = RequestMethod.POST)
    void userDisable(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/user/role-list", method = RequestMethod.POST)
    List<UserRoleResponseVo> userRoleList(@RequestBody DefaultRequestVo request);

    @RequestMapping(value = "/user/role-edit", method = RequestMethod.POST)
    void editUserRole(@RequestBody UserRequestVo request);
}
