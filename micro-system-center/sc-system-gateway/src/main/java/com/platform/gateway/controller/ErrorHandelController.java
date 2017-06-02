package com.platform.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.core.exception.ErrorHolder.CodeTemp;
import com.core.support.web.domain.DefaultResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ErrorHandelController implements ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorHandelController.class);

    private ErrorAttributes errorAttributes = new DefaultErrorAttributes();
    @Value("${error.path:/error}")
    private String errorPath;


    @RequestMapping(value = "/error")
    public String handle(HttpServletRequest request) {
        logger.warn("异常处理：{}", getErrorAttributes(request));
        return JSON.toJSONString(new DefaultResult("网管换机子！", CodeTemp.UNKNOW.getCode()));
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, false);
    }

    @Override
    public String getErrorPath() {
        return this.errorPath;
    }
}
