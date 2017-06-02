package com.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;

@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
                .antMatchers("/management/**", "/oauth/token").permitAll().anyRequest()
                .authenticated().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);

        ClientCredentialsTokenGranter a;
//		auth.authenticationProvider(new CustomerAuthenticationProvider());
//		auth.userDetailsService(new CustomerUserDetailService());
    }


}