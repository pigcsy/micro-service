package com.system.provider.fallback;


import com.core.exception.MicroException;
import com.system.domain.response.menu.MenuResponseVo;
import com.system.provider.client.SystemMenuClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SystemMenuClientFallbackFactory implements FallbackFactory<SystemMenuClient> {


    @Override
    public SystemMenuClient create(Throwable cause) {
        return new SystemMenuClient() {


            @Override
            public MenuResponseVo menu() {
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