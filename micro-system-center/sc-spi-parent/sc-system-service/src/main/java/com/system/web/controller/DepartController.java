package com.system.web.controller;

import com.core.base.AbstractController;
import com.core.support.web.ResponseJson;
import com.system.domain.DefaultRequestDto;
import com.system.domain.request.depart.DepartListRequestDto;
import com.system.domain.request.depart.DepartRequestDto;
import com.system.domain.response.depart.DepartListResponseDto;
import com.system.domain.response.depart.DepartResultDto;
import com.system.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/depart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DepartController extends AbstractController {
    private static final long serialVersionUID = 8592617385717521625L;

    @Autowired
    DepartService departService;

    /**
     * list:(部门分页列表查询). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public DepartListResponseDto list(@RequestBody DepartListRequestDto request) {
        return departService.list(request);
    }


    /**
     * detail:(部门详情). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public DepartResultDto detail(@RequestBody DefaultRequestDto request) {
        return departService.detail(request);
    }

    /**
     * edit:(部门编辑). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public void edit(@RequestBody DepartRequestDto request) {
        departService.edit(request);
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
    @ResponseBody
    @ResponseJson
    public void delete(@RequestBody DefaultRequestDto request) {
        departService.delete(request);
    }

    /**
     * list:(部门查询列表). <br/>
     *
     * @param request
     * @return
     * @author maven
     */
    @RequestMapping(value = "/edit-list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public List<DepartResultDto> editList(@RequestBody DepartRequestDto request) {
        return departService.editList(request);
    }
}
