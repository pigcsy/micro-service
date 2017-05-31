package com.core.support.security;

import com.alibaba.fastjson.JSON;
import com.core.exception.MicroException;
import com.core.support.web.domain.DefaultResult;
import com.core.support.web.domain.ErrorResult;
import com.core.utils.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class ExceptionRenderer {
    // 是否为传递异常
    boolean exceptionTransmit = true;
    ;

    public ExceptionRenderer(boolean exceptionTransmit) {
        this.exceptionTransmit = exceptionTransmit;
    }

    public ExceptionRenderer() {
    }

    public void handleHttpEntityResponse(HttpServletResponse response, MicroException exception, HttpStatus httpStatus) {
        if (exceptionTransmit) {
            WebUtils.sendJson(response, JSON.toJSONString(new ErrorResult(exception, httpStatus)),
                    HttpStatus.OK);
        } else {
            WebUtils.sendJson(response,
                    JSON.toJSONString(new DefaultResult<>(exception.getMessage(), exception.getErrorCode())),
                    httpStatus);
        }

    }
}
