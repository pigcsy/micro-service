package com.platform.gateway.security;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.client.admin")
public class Oauth2ClientConfig {
    private String grantType;
    private String clientId;
    private String clientSecret;

    public Map<String, List<String>> toRequestQueryParams() {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        params.put("grant_type", Lists.newArrayList(grantType));
        params.put("client_id", Lists.newArrayList(clientId));
        params.put("client_secret", Lists.newArrayList(clientSecret));
        return params;
    }

}
