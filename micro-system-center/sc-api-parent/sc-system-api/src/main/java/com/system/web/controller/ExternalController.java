package com.system.web.controller;

import com.core.base.AbstractController;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.external.ExternalListRequestVo;
import com.system.domain.request.external.ExternalRequestVo;
import com.system.domain.response.external.ExternalListResponseVo;
import com.system.domain.response.external.ExternalResult;
import com.system.manager.ExternalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/external", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RefreshScope
public class ExternalController extends AbstractController {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    ExternalManager externalManager;


    /**
     * 用户列表
     *
     * @author maven
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ExternalListResponseVo list(ExternalListRequestVo request) {
        return externalManager.list(request);
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
    public ExternalResult detail(DefaultRequestVo request) {
        return externalManager.detail(request);
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
    public void edit(ExternalRequestVo request) {
        externalManager.edit(request);
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
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(DefaultRequestVo request) {
        externalManager.delete(request);
    }


}
