package com.core.support.hystrix;

import com.core.base.MicroSecurityContext;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class SecurityContextHystrixRequestVariableSetterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        SecurityContextHystrixRequestVariable.getInstance().set(MicroSecurityContext.getInterface());
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
