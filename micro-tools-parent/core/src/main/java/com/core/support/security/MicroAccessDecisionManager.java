package com.core.support.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class MicroAccessDecisionManager implements AccessDecisionManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null)
            return;
        if (configAttributes.stream().anyMatch(config -> authentication.getAuthorities().stream()
                .anyMatch(auth -> config.getAttribute().equals(auth.getAuthority())))) {
            logger.info("验证通过");
        } else {
            throw new AccessDeniedException("没有权限,拒绝访问!");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}
