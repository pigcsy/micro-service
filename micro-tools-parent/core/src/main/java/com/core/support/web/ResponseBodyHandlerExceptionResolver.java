package com.core.support.web;

import com.google.common.collect.Lists;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * @author Administrator
 */
public class ResponseBodyHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {

    @Override
    public void afterPropertiesSet() {
        super.setReturnValueHandlers(Lists.newArrayList(new ResponseBodyExceptionWrapHandler()));
        super.afterPropertiesSet();
    }

}
