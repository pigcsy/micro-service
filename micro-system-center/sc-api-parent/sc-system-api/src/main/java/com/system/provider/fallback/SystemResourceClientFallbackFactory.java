package com.system.provider.fallback;


import com.core.exception.MicroException;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.resourse.ResourcesListRequestVo;
import com.system.domain.request.resourse.ResourcesRequestVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.domain.response.resource.ResourcesListResponseVo;
import com.system.provider.client.SystemResourceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemResourceClientFallbackFactory implements FallbackFactory<SystemResourceClient> {


    @Override
    public SystemResourceClient create(Throwable cause) {
        return new SystemResourceClient() {

            @Override
            public ResourcesListResponseVo list(ResourcesListRequestVo request) {
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
            public ResourcesDetailResponseVo detail(DefaultRequestVo request) {
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
            public void edit(ResourcesRequestVo request) {
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
            public ResourcesDetailResponseVo search(ResourcesRequestVo request) {
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
            public void delete(DefaultRequestVo request) {
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
            public List<ResourcesDetailResponseVo> editList(ResourcesRequestVo request) {
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