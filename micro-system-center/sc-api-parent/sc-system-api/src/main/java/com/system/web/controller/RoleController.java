package com.system.web.controller;

import com.core.base.AbstractController;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.role.RoleListRequestVo;
import com.system.domain.request.role.RoleRequestVo;
import com.system.domain.response.role.RoleListResponseVo;
import com.system.domain.response.role.RoleResourcesResponseVo;
import com.system.manager.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class RoleController extends AbstractController {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    RoleManager roleManager;

    /**
     * list:(角色列表查询). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RoleListResponseVo list(RoleListRequestVo request) {
        return roleManager.list(request);
    }

    /**
     * edit:(角色编辑). <br/>
     *
     * @param request
     * @param result
     * @return
     * @author maven
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(@Validated(RoleRequestVo.ValiAdminRoleEdit.class) RoleRequestVo request, BindingResult result) {
        roleManager.edit(request);
    }

    /**
     * delete:(删除角色). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(DefaultRequestVo request) {
        roleManager.delete(request);
    }

    /**
     * getRoleResourceIds:(获取角色资源). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/resource-list")
    public RoleResourcesResponseVo resourceList(DefaultRequestVo request) {
        return roleManager.resourceList(request);
    }

    /**
     * editRoleResourceIds:(编辑角色权限). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/resource-edit", method = RequestMethod.POST)
    public void resourceEdit(RoleListRequestVo request) {
        roleManager.resourceEdit(request);
    }

}
