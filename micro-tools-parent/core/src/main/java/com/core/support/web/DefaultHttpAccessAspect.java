package com.core.support.web;

import com.core.utils.WebUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Clock;



@Aspect
@Configuration
@Component
public class DefaultHttpAccessAspect {
    private final String req_start_time = "req_start_time";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * 定义一个切入点
     */
    @Pointcut("execution(* com.cmy.*.web.controller..*.*(..))")
    public void point() {

    }

    @Before("point()")
    public void before(JoinPoint jp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        logger.info("[url]{}[url][]{}[]",request.getRequestURL(), WebUtils.getRequestParams(request));
        request.setAttribute(req_start_time, Clock.systemUTC().millis());
    }

    /**
     * @param data
     * @throws HttpMessageNotWritableException
     * @throws IOException
     */
    @AfterReturning(pointcut = "point()", returning = "data")
    public void after(JoinPoint jp, Object data) throws HttpMessageNotWritableException, IOException {
        logger.info("结束");
        printConsumeTime();
    }

    @AfterThrowing(pointcut = "point()", throwing = "error")
    public void exception(JoinPoint jp, Throwable error) throws Throwable {
        printConsumeTime();
    }

    /**
     * 打印消费时间
     */
    private void printConsumeTime() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Long startTm = (Long) request.getAttribute(req_start_time);
        logger.info("消费时间:--------------------->" + (Clock.systemUTC().millis() - startTm));
    }

}
