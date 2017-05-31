package com.core.support.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.core.constants.HttpConstant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 会话传递
 *
 * @author Administrator
 */
@Configuration
@Import(AuthorizationConverter.class)
public class TransmitAuthenticationProcessingConfiguration {
    @Autowired
    AuthorizationConverter authorizationConverter;

    @Bean
    public FilterRegistrationBean registrationTransmitAuthenticationProcessingFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new TransmitAuthenticationProcessingFilter());// ServletName默认值为首字母小写，即myServlet
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Integer.MAX_VALUE - 10);
        return filterRegistrationBean;
    }

    public class TransmitAuthenticationProcessingFilter implements Filter, InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            // TODO Auto-generated method stub

        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            // TODO Auto-generated method stub
        }

        @Override
        public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) arg0;
            try {
                if (request.getHeaders(HttpConstant.INTERNAL_AUTHORIZATION) != null && request.getHeaders(HttpConstant.INTERNAL_AUTHORIZATION).hasMoreElements()) {
                    String authorization = request.getHeaders(HttpConstant.INTERNAL_AUTHORIZATION).nextElement();
                    CurrentPrincipalHolder.setPrincipal(authorizationConverter.convertPrincipal(authorization));
                }

            } catch (Exception e) {

            }

            chain.doFilter(arg0, arg1);

        }

        @Override
        public void destroy() {
            // TODO Auto-generated method stub

        }

    }

}
