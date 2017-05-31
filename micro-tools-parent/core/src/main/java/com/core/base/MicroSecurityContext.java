package com.core.base;

import com.core.support.web.domain.ErrorResult;
import com.core.support.security.CurrentPrincipalHolder;
import com.core.support.security.DefaultCurrentPrincipal;
import lombok.Data;


@Data
public class MicroSecurityContext {
    public DefaultCurrentPrincipal currentPrincipal;
    public ErrorResult errorResult;

    public static MicroSecurityContext getInterface() {
        MicroSecurityContext microSecurityContext = new MicroSecurityContext();
        microSecurityContext.setCurrentPrincipal(CurrentPrincipalHolder.getPrincipal());
        return microSecurityContext;
    }
}
