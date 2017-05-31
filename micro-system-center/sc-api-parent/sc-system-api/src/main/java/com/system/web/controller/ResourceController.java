package com.system.web.controller;

import com.core.base.AbstractController;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.resourse.ResourcesListRequestVo;
import com.system.domain.request.resourse.ResourcesRequestVo;
import com.system.domain.response.resource.EditResourceResponseListVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.domain.response.resource.ResourcesListResponseVo;
import com.system.manager.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class ResourceController extends AbstractController {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    ResourceManager resourceManager;


    /**
     * 资源列表
     *
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResourcesListResponseVo list(ResourcesListRequestVo request) {
        return resourceManager.list(request);
    }

    /**
     * 资源详情
     *
     * @param request
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ResourcesDetailResponseVo detail(DefaultRequestVo request) {
        return resourceManager.detail(request);
    }


    /**
     * 编辑资源
     *
     * @param request
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(ResourcesRequestVo request) {
        resourceManager.edit(request);
    }

    /**
     * 资源删除
     *
     * @param request
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(DefaultRequestVo request) {
        resourceManager.delete(request);
    }

    /**
     * 搜索资源
     *
     * @param request
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResourcesDetailResponseVo versionSearch(ResourcesRequestVo request) {
        return resourceManager.search(request);
    }


    /**
     * queryAdminResources:(修改或新增时树型结构). <br/>
     *
     * @param resourcesRequestVo
     * @return
     * @author "maven"
     */
    @RequestMapping(value = "/edit-list", method = RequestMethod.POST)
    public List<EditResourceResponseListVo> editList(ResourcesRequestVo resourcesRequestVo) {
        return resourceManager.editList(resourcesRequestVo);
    }
}
