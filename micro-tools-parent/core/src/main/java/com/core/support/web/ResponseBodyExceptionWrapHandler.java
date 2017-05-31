package com.core.support.web;

import com.alibaba.fastjson.JSON;
import com.core.support.web.domain.DefaultResult;
import com.core.support.web.domain.ErrorResult;
import com.core.utils.WebUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Administrator
 */
public class ResponseBodyExceptionWrapHandler implements HandlerMethodReturnValueHandler {

    public ResponseBodyExceptionWrapHandler() {
    }

    /**
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    /**
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        DefaultResult<?> result = null;
        if (returnValue instanceof ResponseEntity) {
            ResponseEntity<?> error = (ResponseEntity<?>) returnValue;
            if (error.getBody() instanceof ErrorResult) {
                ErrorResult errorResult = (ErrorResult) error.getBody();
                result = new DefaultResult<>(errorResult.getMessage(), errorResult.getCode());
            }
        } else {
            result = new DefaultResult<>(returnValue);
        }
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
        WebUtils.sendJson(attributes.getResponse(), JSON.toJSONString(result));
    }

}