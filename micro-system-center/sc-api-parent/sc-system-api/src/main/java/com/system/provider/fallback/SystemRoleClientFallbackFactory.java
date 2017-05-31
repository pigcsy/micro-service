package com.system.provider.fallback;


import com.core.exception.MicroException;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.role.RoleListRequestVo;
import com.system.domain.request.role.RoleRequestVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.domain.response.role.RoleListResponseVo;
import com.system.domain.response.role.RoleResourcesResponseVo;
import com.system.provider.client.SystemRoleClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SystemRoleClientFallbackFactory implements FallbackFactory<SystemRoleClient> {


    @Override
    public SystemRoleClient create(Throwable cause) {
        return new SystemRoleClient() {

            @Override
            public RoleListResponseVo list(RoleListRequestVo request) {
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
            public void edit(RoleRequestVo request) {
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
            public RoleResourcesResponseVo resourceList(DefaultRequestVo request) {
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
            public void resourceEdit(RoleListRequestVo request) {
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