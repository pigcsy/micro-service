package com.safty.provider.support;


import com.core.support.feign.DefaultFeignExceptionHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class FeignExceptionHandler extends DefaultFeignExceptionHandlerInterceptor {

    /*
     * 定义一个切入点
     */
    @Pointcut("execution(* com.*.provider.client..*.*(..))")
    public void point() {

    }


}
