package com.system.provider.fallback;


import com.core.exception.MicroException;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.depart.DepartListRequestVo;
import com.system.domain.request.depart.DepartRequestVo;
import com.system.domain.request.resourse.ResourcesRequestVo;
import com.system.domain.response.depart.DepartListResponseVo;
import com.system.domain.response.depart.DepartResultVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.provider.client.SystemDepartClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class SystemDepartClientFallbackFactory implements FallbackFactory<SystemDepartClient> {

    @Override
    public SystemDepartClient create(Throwable cause) {
        return new SystemDepartClient() {


            @Override
            public DepartListResponseVo list(@RequestBody DepartListRequestVo request) {
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
            public DepartResultVo detail(@RequestBody DefaultRequestVo request) {
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
            public void edit(@RequestBody DepartRequestVo request) {
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
            public ResourcesDetailResponseVo search(@RequestBody ResourcesRequestVo request) {
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

            @Override
            public List<DepartResultVo> editList(@RequestBody DepartRequestVo request) {
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