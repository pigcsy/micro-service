package com.core.support.security;

public class CurrentPrincipalHolder {
    private static final ThreadLocal<DefaultCurrentPrincipal> contextHolder = new ThreadLocal<DefaultCurrentPrincipal>();

    public static DefaultCurrentPrincipal getPrincipal() {
        return contextHolder.get();
    }

    public static void setPrincipal(DefaultCurrentPrincipal principal) {
        if (principal == null) return;
        contextHolder.set(principal);
    }

    public static void clear() {
        contextHolder.remove();
    }
}
