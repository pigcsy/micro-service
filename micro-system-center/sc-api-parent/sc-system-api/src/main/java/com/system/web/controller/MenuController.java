/**
 * Project Name:saas-app-api
 * File Name:TestController.java
 * Package Name:com.yxt.app.web.controller
 * Date:2016年10月17日下午8:18:20
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.web.controller;

import com.core.base.AbstractController;
import com.system.domain.response.menu.MenuResponseVo;
import com.system.manager.MenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author csy
 * @date: 2016年10月17日 下午8:18:20 <br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope

public class MenuController extends AbstractController {
    @Autowired
    MenuManager menuManager;

    @Value("${service.rsa.publicKey}")
    private String rasPublicKey;

    @Value("${service.rsa.privateKey}")
    private String rsaPrivateKey;

    @RequestMapping("/list")
    public MenuResponseVo menu() {
        return menuManager.menu();
    }

    @RequestMapping("/init")
    public String init() {
        return rasPublicKey;
    }
}
