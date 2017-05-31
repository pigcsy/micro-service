package com.system.web.controller;

import com.core.base.AbstractController;
import com.core.support.web.ResponseJson;
import com.system.domain.DefaultRequestDto;
import com.system.domain.request.resource.ResourcesListRequestDto;
import com.system.domain.request.resource.ResourcesRequestDto;
import com.system.domain.response.resource.ResourcesDetailResponseDto;
import com.system.domain.response.resource.ResourcesListResponseDto;
import com.system.service.ResourceService;
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
@RequestMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResourceController extends AbstractController {
    private static final long serialVersionUID = 7008036874171358260L;

    @Autowired
    ResourceService resourceService;


    /**
     * 资源列表
     *
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public ResourcesListResponseDto list(@RequestBody ResourcesListRequestDto request) {
        return resourceService.list(request);
    }

    /**
     * 资源详情
     *
     * @param request
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public ResourcesDetailResponseDto detail(@RequestBody DefaultRequestDto request) {
        return resourceService.detail(request);
    }


    /**
     * 编辑资源
     *
     * @param request
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public void edit(@RequestBody ResourcesRequestDto request) {
        resourceService.edit(request);
    }

    /**
     * 资源删除
     *
     * @param request
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public void delete(@RequestBody DefaultRequestDto request) {
        resourceService.delete(request);
    }

    /**
     * 搜索资源
     *
     * @param request
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public ResourcesDetailResponseDto versionSearch(@RequestBody ResourcesRequestDto request) {
        return resourceService.search(request);
    }


    /**
     * queryAdminResources:(修改或新增时树型结构). <br/>
     *
     * @param resourcesRequestDto
     * @return
     * @author "maven"
     */
    @RequestMapping(value = "/edit-list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    public List<ResourcesDetailResponseDto> editList(@RequestBody ResourcesRequestDto resourcesRequestDto) {
        return resourceService.editList(resourcesRequestDto);
    }
}
