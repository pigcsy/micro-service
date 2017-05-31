package com.system.web.controller;

import com.core.base.AbstractController;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.user.UserListRequestVo;
import com.system.domain.request.user.UserRequestVo;
import com.system.domain.response.user.UserListResponseVo;
import com.system.domain.response.user.UserResult;
import com.system.domain.response.user.UserRoleResponseVo;
import com.system.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class UserController extends AbstractController {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    UserManager userManager;


    /**
     * 用户列表
     *
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public UserListResponseVo list(UserListRequestVo request) {
        return userManager.list(request);
    }

    /**
     * detail:(用户详情). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author maven
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public UserResult detail(DefaultRequestVo request) {
        return userManager.detail(request);
    }


    /**
     * edit:(用户编辑). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author maven
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editOrInsertUser(@Validated(UserRequestVo.AdminStaffEdit.class) UserRequestVo request, BindingResult result) throws Exception {
        userManager.editOrInsertUser(request);
    }

    /**
     * disable:(用户限制). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author maven
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public void userDisable(DefaultRequestVo request) {
        userManager.userDisable(request);
    }

    /**
     * getStaffRoleList:(用户角色列表查询). <br/>
     *
     * @author maven
     */
    @RequestMapping(value = "/role-list", method = RequestMethod.POST)
    public List<UserRoleResponseVo> userRoleList(DefaultRequestVo request) {
        return userManager.userRoleList(request);
    }

    /**
     * editStaffRole:(设置用户角色). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author maven
     */
    @RequestMapping(value = "/role-edit", method = RequestMethod.POST)
    public void editStaffRole(@Validated(UserRequestVo.AdminStaffDetail.class) UserRequestVo request, BindingResult result) {
        userManager.editUserRole(request);
    }

}
