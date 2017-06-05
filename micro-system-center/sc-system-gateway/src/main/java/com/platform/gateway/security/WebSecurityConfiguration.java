package com.platform.gateway.security;

import com.platform.gateway.security.client.userOauth.CustomOAuth2ClientAuthenticationProcessingFilter;
import com.platform.gateway.security.client.userOauth.CustomPasswordRestTemplateFactory;
import com.platform.gateway.security.client.userSecurity.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    SecurityInterceptor securityInterceptor;
    @Autowired
    SecurityMetadataSource securityMetadataSource;

    @Autowired
    CustomPasswordRestTemplateFactory customPasswordRestTemplateFactory;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(oauth2Filter());
        filter.setFilters(filters);
        return filter;
    }

    @Bean
    public AbstractAuthenticationProcessingFilter oauth2Filter() {
        OAuth2RestOperations restTemplate = customPasswordRestTemplateFactory.getUserInfoRestTemplate();
        CustomOAuth2ClientAuthenticationProcessingFilter filter = new CustomOAuth2ClientAuthenticationProcessingFilter("/login");
        filter.setRestTemplate(restTemplate);
        filter.setContext(oauth2ClientContext);
        filter.setApplicationEventPublisher(this.applicationContext);
        return filter;
    }

    /**
     * 拦截未认证请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/management/**", "/login")
                .permitAll().anyRequest().authenticated().and().cors().and().csrf().disable();
        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
