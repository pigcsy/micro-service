package com.platform.gateway.filter;

import com.common.support.enums.SystemEn;
import com.core.constants.HttpConstant;
import com.core.support.security.AuthorizationConverter;
import com.core.support.security.DefaultCurrentPrincipal;
import com.core.utils.IPUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.platform.gateway.provider.client.AccessClient;
import com.platform.gateway.provider.request.AccessRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Import(value = {AuthorizationConverter.class})
public class AccessFilter extends ZuulFilter {
    @Autowired
    AuthorizationConverter authorizationConverter;


    @Autowired
    private AccessClient accessClient;

    @Value("${spring.application.name:unknown}")
    private String gatewayName;

    @Value("${access.control.number.daily: 300}")
    private long dailyLimitNum;
    @Value("${access.control.number.minute: 10}")
    private long minuteLimitNum;

    public AccessFilter() {
    }

    public static boolean hasAuthorized() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && SecurityContextHolder
                .getContext().getAuthentication().getPrincipal() instanceof DefaultCurrentPrincipal;
    }

    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        request.setAttribute("req_time", System.currentTimeMillis());
        // 已授权
        if (hasAuthorized()) {
            ctx.addZuulRequestHeader(HttpConstant.INTERNAL_AUTHORIZATION, authorizationConverter.serializePrincipal(getPrincipal()));
        }

        checkLimit();

        return null;
    }

    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return ZuulTypeEn.pre.getMean();
    }

    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 9999;
    }

    private DefaultCurrentPrincipal getPrincipal() {
        if (hasAuthorized()) {
            return (DefaultCurrentPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }

    /**
     * 接口频率访问控制
     */
    private void checkLimit() {
        DefaultCurrentPrincipal principal = getPrincipal();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        AccessRequestVo vo;
        if (principal != null) {
            vo = new AccessRequestVo();
            vo.setGateway(gatewayName);
            vo.setCustomerId(principal.getUid().toString());
            vo.setSystemCode(principal.getSystemType());
            vo.setDailyLimitNum(Long.valueOf(principal.getDailyAccessNum()));
            vo.setMinLimitNum(Long.valueOf(principal.getMinuteAccessNum()));
        } else {
            // 未授权
            String ip = IPUtils.getIpAddr(request);
            vo = new AccessRequestVo();
            vo.setGateway(gatewayName);
            vo.setCustomerId(ip);
            vo.setSystemCode(SystemEn.TOURIST.getCode());
            vo.setDailyLimitNum(dailyLimitNum);
            vo.setMinLimitNum(minuteLimitNum);
        }
        accessClient.checkAccessLimit(vo);
    }
}
