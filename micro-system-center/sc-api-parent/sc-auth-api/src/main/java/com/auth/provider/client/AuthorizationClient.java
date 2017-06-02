package com.auth.provider.client;

import com.auth.provider.domain.system.AdminStaffVo;
import com.auth.provider.domain.system.OauthSystemVo;
import com.auth.provider.fallback.AuthorizationFallbackFactory;
import com.auth.provider.support.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "sc-system-service", fallbackFactory = AuthorizationFallbackFactory.class, configuration = FeignClientConfiguration.class)
public interface AuthorizationClient {


    /**
     * 获取授权系统信息
     *
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/auth/queryClientDetailsById", method = RequestMethod.POST)
    OauthSystemVo getOauthSystem(@RequestParam("clientId") String clientId);

    /**
     * 获取员工信息
     *
     * @param staffName
     * @return
     */
    @RequestMapping(value = "/auth/staff-queryByName", method = RequestMethod.POST)
    AdminStaffVo getAdminStaff(@RequestParam("code") String staffName);

    /**
     * 员工角色
     *
     * @return
     */
    @RequestMapping(value = "/auth/queryRoleByStaffId", method = RequestMethod.POST)
    List<String> getStaffAuthority(@RequestParam("staffId") Integer staffId);


}
