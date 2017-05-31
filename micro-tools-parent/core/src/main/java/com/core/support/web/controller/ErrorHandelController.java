package com.core.support.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorHandelController implements ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorHandelController.class);

    private ErrorAttributes errorAttributes = new DefaultErrorAttributes();
    @Value("${error.path:/error}")
    private String errorPath;


    @RequestMapping(value = "/error")
    @ResponseBody
    public void handle(HttpServletRequest request) {
        logger.warn("异常处理");

    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
//        errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace)
        return this.errorAttributes.getErrorAttributes(requestAttributes, false);
    }

    @Override
    public String getErrorPath() {
        return this.errorPath;
    }
}
