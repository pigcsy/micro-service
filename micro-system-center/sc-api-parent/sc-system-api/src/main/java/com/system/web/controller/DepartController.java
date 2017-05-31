package com.system.web.controller;


import com.core.base.AbstractController;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.depart.DepartListRequestVo;
import com.system.domain.request.depart.DepartRequestVo;
import com.system.domain.response.depart.DepartListResponseVo;
import com.system.domain.response.depart.DepartResultVo;
import com.system.manager.DepartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: AdminStaffController <br/>
 * Function: 部门管理 <br/>
 *
 * @author maven
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/depart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class DepartController extends AbstractController {
    @Autowired
    DepartManager departManager;

    /**
     * list:(部门分页列表查询). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DepartListResponseVo list(DepartListRequestVo request) {
        return departManager.list(request);
    }


    /**
     * detail:(部门详情). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public DepartResultVo detail(DefaultRequestVo request) {
        return departManager.detail(request);
    }

    /**
     * edit:(部门编辑). <br/>
     *
     * @param request
     * @param result
     * @return
     * @author maven
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(@Validated(DepartRequestVo.ValiDepartEdit.class) DepartRequestVo request, BindingResult result) {
        departManager.edit(request);
    }

    /**
     * delete:(删除部门). <br/>
     * 有员工的部门不能删除.<br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(DefaultRequestVo request) {
        departManager.delete(request);
    }

    /**
     * list:(部门分页列表查询). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/edit-list", method = RequestMethod.POST)
    public List<DepartResultVo> editList(DepartRequestVo request) {
        return departManager.editList(request);
    }
}
