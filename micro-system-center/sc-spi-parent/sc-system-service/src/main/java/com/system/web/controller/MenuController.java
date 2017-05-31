package com.system.web.controller;

import com.core.base.AbstractController;
import com.core.support.web.ResponseJson;
import com.system.domain.response.menu.MenuResponseDto;
import com.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController extends AbstractController {


    private static final long serialVersionUID = -4285977837866988642L;
    @Autowired
    MenuService menuService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    MenuResponseDto menu() {
        return menuService.menu();
    }

}
