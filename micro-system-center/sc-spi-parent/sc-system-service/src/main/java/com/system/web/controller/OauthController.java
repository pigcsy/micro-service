package com.system.web.controller;


import com.core.base.AbstractController;
import com.system.domain.response.oath.OauthSystemResponseDto;
import com.system.domain.response.resource.ResourcesResponseDto;
import com.system.domain.response.user.UserResult;
import com.system.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class OauthController extends AbstractController {

    private static final long serialVersionUID = 7592091930789116704L;

    @Autowired
    OauthService oauthService;

    /**
     * 根据用户姓名查询用户信息
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/staff-queryByName", method = RequestMethod.POST)
    UserResult queryByName(String code) {
        return oauthService.queryByName(code);
    }

    /**
     * 根据用户id查询用户资源权限
     *
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/queryResourcesById", method = RequestMethod.POST)
    List<ResourcesResponseDto> queryResourcesById(Integer staffId) {
        return oauthService.queryResourcesById(staffId);
    }

    /**
     * 根据clientid 查询详细
     *
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/queryClientDetailsById", method = RequestMethod.POST)
    OauthSystemResponseDto queryClientDetailsById(String clientId) {
        return oauthService.queryClientDetailsById(clientId);
    }


    /**
     * 获取某用户所有角色
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/queryRoleByStaffId", method = RequestMethod.POST)
    List<String> queryRoleByStaffId(Integer staffId) {
        return oauthService.queryRoleByStaffId(staffId);
    }

}
