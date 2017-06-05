package com.platform.gateway.security;

import com.alibaba.fastjson.JSON;
import com.cache.support.redis.RedisCacheManager;
import com.cache.support.vo.redis.key.RedisKeyTemplate;
import com.cache.support.vo.redis.resource.MetaDataSource;
import com.common.support.enums.SystemEn;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap = new HashMap<>();
    @Autowired
    RedisCacheManager cacheManager;

    @PostConstruct
    public void init() {
        List<MetaDataSource> list = null;
        String cacheReource = cacheManager.get(RedisKeyTemplate.AUTHORIZATION_METADATA_SOURCE.getRedisKey());
        if (StringUtils.isBlank(cacheReource)) {
            //拦截所有请求，无权访问
            resourceMap.put(new AntPathRequestMatcher("/**"), SecurityConfig.createList("ROLE_ILLEGAL"));
            return;
        }
        list = JSON.parseArray(cacheReource, MetaDataSource.class);
        Map<String, List<MetaData>> metasMap = list.stream().filter(metaData -> CollectionUtils.isNotEmpty(metaData.getUrls()))
                .map(metaData -> new MetaDataSource("ROLE_".concat(SystemEn.toEnum(metaData.getSystemType()).getRolePrefix()).concat(metaData.getRole()),
                        metaData.getUrls()))
                .flatMap(mapper -> mapper.getUrls().stream().filter(url -> StringUtils.isNotBlank(url))
                        .map(url -> new MetaData(url, mapper.getRole()))).collect(Collectors.groupingBy(p -> p.getRole()));
        for (Entry<String, List<MetaData>> entry : metasMap.entrySet()) {
            List<String> roles = entry.getValue().stream().map(v -> v.getUrl()).collect(Collectors.toList());
            resourceMap.put(new AntPathRequestMatcher(entry.getKey()), SecurityConfig.createList(roles.toArray(new String[roles.size()])));
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Iterator<RequestMatcher> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            RequestMatcher requestMatcher = ite.next();
            if (requestMatcher.matches(request)) {
                return resourceMap.get(requestMatcher);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Data
    public static class MetaData {
        private String role;
        private String url;

        public MetaData(String role, String url) {
            this.role = role;
            this.url = url;
        }
    }
}