package com.core.support.hystrix;

import com.core.base.MicroSecurityContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

public class SecurityContextHystrixRequestVariable {
    private static final HystrixRequestVariableDefault<MicroSecurityContext> securityContextVariable = new HystrixRequestVariableDefault<>();

    private SecurityContextHystrixRequestVariable() {
    }

    public static HystrixRequestVariableDefault<MicroSecurityContext> getInstance() {
        return securityContextVariable;
    }

}
