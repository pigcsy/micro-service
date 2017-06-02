package com.support.web.controller;

import com.common.support.enums.SystemEn;
import com.core.base.AbstractController;
import com.core.constants.AppConstant;
import com.core.utils.Assert;
import com.support.domain.request.AccessRequest;
import com.support.manager.AccessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/21 上午9:39
 */
@RestController
@RequestMapping("/access")
@RefreshScope
public class AccessController extends AbstractController {
    @Autowired
    private AccessManager accessManager;

    /**
     * 限制检查
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String checkAccessLimit(@RequestBody AccessRequest request) {
        Assert.notBlank(request.getGateway(), "gateway is must not be blank");
        Assert.notBlank(request.getCustomerId(), "customer is must not be blank");
        Assert.notNull(request.getSystemCode(), "system is must not be null");

        SystemEn systemEn = SystemEn.toEnum(request.getSystemCode());
        accessManager.checkAccessLimit(request.getGateway(), request.getCustomerId(), systemEn, request.getMinLimitNum(), request.getDailyLimitNum());
        return AppConstant.SUCCESS;
    }

    /**
     * 增加访问量
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/increment", method = RequestMethod.POST)
    public String increment(@RequestBody AccessRequest request) {
        Assert.notBlank(request.getGateway(), "gateway is must not be blank");
        Assert.notBlank(request.getCustomerId(), "customer is must not be blank");
        Assert.notNull(request.getSystemCode(), "system is must not be null");
        SystemEn systemEn = SystemEn.toEnum(request.getSystemCode());
        accessManager.increaseAccessAll(request.getGateway(), request.getCustomerId(), systemEn);
        return AppConstant.SUCCESS;
    }
}
