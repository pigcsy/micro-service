package com.core.support.feign;

import com.core.base.MicroSecurityContext;
import com.core.constants.HttpConstant;
import com.core.exception.MicroException;
import com.core.support.hystrix.SecurityContextHystrixRequestVariable;
import com.core.support.security.AuthorizationConverter;
import com.core.utils.HttpErrorDecoder;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.Retryer;
import feign.Util;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static feign.FeignException.errorStatus;

public abstract class DefaultFeignClientConfiguration {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AuthorizationConverter authorizationConverter;
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public ErrorDecoder buildErrorDecoder() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                logger.error("feign失败  方法 ：{},原因：{}", methodKey, response);
                try {
                    if (response.body() != null) {
                        MicroException exception = HttpErrorDecoder.decode(HttpStatus.valueOf(response.status()),
                                Util.toString(response.body().asReader()));
                        return new HystrixBadRequestException(exception.getMessage(), exception);
                    }
                } catch (IOException e) {

                }
                return errorStatus(methodKey, response);
            }
        };
    }

    /**
     * feign 默认屏蔽重试 如需重试 new Retryer.Default()
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "feign.retry.enabled", matchIfMissing = false)
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    @ConditionalOnProperty(name = "feign.retry.enabled", matchIfMissing = true)
    Retryer feignDefaultRetryer() {
        return new Retryer.Default();
    }

    @Bean
    public feign.codec.Encoder buildEncoder() {
        return new CustomEncode(messageConverters);
    }

    @Bean
    public Decoder feignDecoder() {
        return new CustomDecode(new SpringDecoder(this.messageConverters));
    }

    /**
     * 处理离线请求,重组会话
     *
     * @return
     */
    @Bean
    public RequestInterceptor transmitAuthorizationInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                MicroSecurityContext securityContext = SecurityContextHystrixRequestVariable.getInstance().get();
                if (securityContext != null && securityContext.getCurrentPrincipal() != null) {
                    requestTemplate.header(HttpConstant.INTERNAL_AUTHORIZATION,
                            authorizationConverter.serializePrincipal(securityContext.getCurrentPrincipal()));
                }
            }
        };
    }
}
