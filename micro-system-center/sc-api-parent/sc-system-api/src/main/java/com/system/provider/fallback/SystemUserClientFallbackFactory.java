package com.system.provider.fallback;


import com.core.exception.MicroException;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.user.UserListRequestVo;
import com.system.domain.request.user.UserRequestVo;
import com.system.domain.response.user.UserListResponseVo;
import com.system.domain.response.user.UserResult;
import com.system.domain.response.user.UserRoleResponseVo;
import com.system.provider.client.SystemUserClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class SystemUserClientFallbackFactory implements FallbackFactory<SystemUserClient> {

    @Override
    public SystemUserClient create(Throwable cause) {
        return new SystemUserClient() {


            @Override
            public UserListResponseVo list(UserListRequestVo code) {
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
            public UserResult detail(DefaultRequestVo request) {
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
            public void editOrInsertUser(UserRequestVo code) {
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
            public void userDisable(DefaultRequestVo request) {
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
            public List<UserRoleResponseVo> userRoleList(@RequestBody DefaultRequestVo request) {
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
            public void editUserRole(UserRequestVo code) {
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