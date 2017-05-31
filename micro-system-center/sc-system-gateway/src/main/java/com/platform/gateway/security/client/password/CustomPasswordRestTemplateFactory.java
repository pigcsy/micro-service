package com.platform.gateway.security.client.password;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@Configuration
@Primary
public class CustomPasswordRestTemplateFactory {

	private final OAuth2ClientContext oauth2ClientContext;

	private OAuth2RestTemplate template;
	@Autowired
	LoadBalancerClient loadBalancerClient;
	@Autowired
	LoadBalancerRequestFactory requestFactory;

	public LoadBalancerInterceptor ribbonInterceptor() {
		return new LoadBalancerInterceptor(loadBalancerClient, requestFactory);
	}

	@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	public ResourceOwnerPasswordResourceDetails getDetail() {
		return new ResourceOwnerPasswordResourceDetails();
	}

	public CustomPasswordRestTemplateFactory(ObjectProvider<OAuth2ClientContext> oauth2ClientContextProvider) {
		this.oauth2ClientContext = oauth2ClientContextProvider.getIfAvailable();
	}

	public OAuth2RestTemplate getUserInfoRestTemplate() {
		if (this.template == null) {
			this.template = getTemplate();
			ResourceOwnerPasswordAccessTokenProvider accessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
			accessTokenProvider.setInterceptors(com.google.common.collect.Lists.newArrayList(ribbonInterceptor()));
			this.template.setAccessTokenProvider(accessTokenProvider);
		}
		return this.template;
	}

	private OAuth2RestTemplate getTemplate() {
		return new OAuth2RestTemplate(this.getDetail(), this.oauth2ClientContext);
	}
}
