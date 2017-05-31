package com.system.web.controller;

import com.core.base.AbstractController;
import com.core.support.web.ResponseJson;
import com.system.domain.DefaultRequestDto;
import com.system.domain.request.role.RoleListRequestDto;
import com.system.domain.request.role.RoleRequestDto;
import com.system.domain.response.role.RoleListResponseDto;
import com.system.domain.response.role.RoleResourcesResponseDto;
import com.system.service.RoleService;
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
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController extends AbstractController {

    private static final long serialVersionUID = 3230288529259312779L;

    @Autowired
    RoleService roleService;


    /**
     * list:(角色列表查询). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public RoleListResponseDto list(@RequestBody RoleListRequestDto request) {
        return roleService.list(request);
    }

    /**
     * edit:(角色编辑). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public void edit(@RequestBody RoleRequestDto request) {
        roleService.edit(request);
    }


    /**
     * delete:(删除角色). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public void delete(@RequestBody DefaultRequestDto request) {
        roleService.delete(request);
    }

    /**
     * getRoleResourceIds:(获取角色资源). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/resource-list")
    @ResponseBody
    @ResponseJson
    public RoleResourcesResponseDto resourceList(@RequestBody DefaultRequestDto request) {
        return roleService.resourceList(request);
    }

    /**
     * editRoleResourceIds:(编辑角色权限). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/resource-edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public void resourceEdit(@RequestBody RoleListRequestDto request) {
        roleService.resourceEdit(request);
    }


}
