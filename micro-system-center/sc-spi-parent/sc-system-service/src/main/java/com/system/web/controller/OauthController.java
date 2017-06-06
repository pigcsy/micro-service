package com.system.web.controller;


import com.core.base.AbstractController;
import com.system.domain.response.oath.OauthSystemResponseDto;
import com.system.service.impl.OauthServiceImpl;
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
    OauthServiceImpl oauthService;

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
    List<String> queryRoleByUserId(Integer userId) {
        return oauthService.queryRoleByUserId(userId);
    }

}
