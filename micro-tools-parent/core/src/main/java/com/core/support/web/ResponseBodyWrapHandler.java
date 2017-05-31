package com.core.support.web;

import com.alibaba.fastjson.JSON;
import com.core.support.web.domain.DefaultResult;
import com.core.utils.WebUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {

    public ResponseBodyWrapHandler() {
    }

    /**
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasMethodAnnotation(ResponseJson.class);
    }

    /**
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        DefaultResult<?> result = new DefaultResult<>(returnValue);
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes());
        WebUtils.sendJson(attributes.getResponse(), JSON.toJSONString(result));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(
                    ((RequestMappingHandlerAdapter) bean).getReturnValueHandlers());
            ResponseBodyWrapHandler jsonHandler = null;
            for (int i = 0; i < handlers.size(); i++) {
                HandlerMethodReturnValueHandler handler = handlers.get(i);
                if (handler instanceof ResponseBodyWrapHandler) {
                    jsonHandler = (ResponseBodyWrapHandler) handler;
                    break;
                }
            }
            if (jsonHandler != null) {
                handlers.remove(jsonHandler);
                handlers.add(0, jsonHandler);
                ((RequestMappingHandlerAdapter) bean).setReturnValueHandlers(handlers);
            }
        }
        return bean;
    }
}