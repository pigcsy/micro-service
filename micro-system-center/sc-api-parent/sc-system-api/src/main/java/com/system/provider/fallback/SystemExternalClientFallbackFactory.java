package com.system.provider.fallback;


import com.core.exception.MicroException;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.external.ExternalListRequestVo;
import com.system.domain.request.external.ExternalRequestVo;
import com.system.domain.response.external.ExternalListResponseVo;
import com.system.domain.response.external.ExternalResult;
import com.system.provider.client.SystemExternalClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class SystemExternalClientFallbackFactory implements FallbackFactory<SystemExternalClient> {


    @Override
    public SystemExternalClient create(Throwable cause) {
        return new SystemExternalClient() {


            @Override
            public ExternalListResponseVo list(@RequestBody ExternalListRequestVo request) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    /**
                     * TODO 可做回退处理，不抛出异常
                     */
                    throw new RuntimeException(cause);
                }
            }

            @Override
            public ExternalResult detail(@RequestBody DefaultRequestVo request) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    /**
                     * TODO 可做回退处理，不抛出异常
                     */
                    throw new RuntimeException(cause);
                }
            }

            @Override
            public void edit(@RequestBody ExternalRequestVo request) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    /**
                     * TODO 可做回退处理，不抛出异常
                     */
                    throw new RuntimeException(cause);
                }
            }

            @Override
            public void delete(@RequestBody DefaultRequestVo request) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    /**
                     * TODO 可做回退处理，不抛出异常
                     */
                    throw new RuntimeException(cause);
                }
            }


        };
    }
}