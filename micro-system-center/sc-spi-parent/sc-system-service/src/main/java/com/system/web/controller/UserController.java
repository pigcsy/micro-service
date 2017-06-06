package com.system.web.controller;

import com.core.base.AbstractController;
import com.core.support.web.ResponseJson;
import com.system.domain.DefaultRequestDto;
import com.system.domain.request.user.UserListRequestDto;
import com.system.domain.request.user.UserRequestDto;
import com.system.domain.response.user.UserListResponseDto;
import com.system.domain.response.user.UserResult;
import com.system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends AbstractController {

    private static final long serialVersionUID = -1847490836635164716L;

    @Autowired
    UserServiceImpl userService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    UserListResponseDto list(@RequestBody UserListRequestDto request) {
        return userService.list(request);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    UserResult detail(@RequestBody DefaultRequestDto request) {
        return userService.detail(request);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    void editOrInsertUser(@RequestBody UserRequestDto request) {
        userService.editOrInsertUser(request);
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    void disable(@RequestBody DefaultRequestDto request) {
        userService.disable(request);
    }


    /**
     * 根据用户姓名查询用户信息
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/staff-queryByName", method = RequestMethod.POST)
    UserResult queryByName(String code) {
        return userService.queryByName(code);
    }

}
