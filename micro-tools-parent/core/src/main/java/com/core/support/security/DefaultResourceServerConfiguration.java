package com.core.support.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class DefaultResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    RedisConnectionFactory connectionFactory;
    @Value(value = "${security.oauth2.resource.id:}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(null);
//		OAuth2AuthenticationProcessingFilter
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(new RedisTokenStore(connectionFactory));
        resources.tokenServices(services);
        resources.accessDeniedHandler(new MicroAccessDeniedHandler(new ExceptionRenderer(false)))
                .authenticationEntryPoint(new MicroAuthenticationEntryPoint(new ExceptionRenderer(false)));

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(new MicroAuthenticationEntryPoint(new ExceptionRenderer(false)));
        http.authorizeRequests().antMatchers("/management/**").permitAll().anyRequest().authenticated().and().csrf().disable();
    }

}