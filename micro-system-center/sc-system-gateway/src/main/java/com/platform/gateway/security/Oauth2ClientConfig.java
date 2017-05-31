package com.platform.gateway.security;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="security.oauth2.client.admin")
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
// 	public static void main(String[] args) {
// 		String a = URLEncoder.encode("a/b");
// 		System.err.println(a);
// //		System.err.println(URLDecoder.decode(a));
// 	}

}
