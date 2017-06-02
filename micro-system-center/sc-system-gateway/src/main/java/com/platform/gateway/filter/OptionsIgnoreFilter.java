package com.platform.gateway.filter;

import com.core.utils.WebUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OptionsIgnoreFilter implements Filter, InitializingBean {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authUrl = WebUtils.getPathWithinApplication(request);
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            preSetHeader(response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private void preSetHeader(HttpServletResponse response) {
        response.addHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.CONTENT_TYPE);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Authorization,Source,Content-type,X-Requested-With,DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type, Accept-Language, Origin, Accept-Encoding");
        response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
