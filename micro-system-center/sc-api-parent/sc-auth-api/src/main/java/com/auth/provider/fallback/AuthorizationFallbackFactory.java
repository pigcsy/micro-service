package com.auth.provider.fallback;

import com.auth.provider.client.AuthorizationClient;
import com.auth.provider.domain.system.AdminStaffVo;
import com.auth.provider.domain.system.OauthSystemVo;
import com.core.exception.MicroException;
import feign.hystrix.FallbackFactory;

import java.util.List;

public class AuthorizationFallbackFactory implements FallbackFactory<AuthorizationClient> {

    @Override
    public AuthorizationClient create(Throwable cause) {
        return new AuthorizationClient() {

            @Override
            public OauthSystemVo getOauthSystem(String clientId) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    throw new RuntimeException(cause);
                }
            }

            @Override
            public AdminStaffVo queryByUserName(String userNm) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    cause.printStackTrace();
                    throw new RuntimeException(cause);
                }
            }

            @Override
            public List<String> getStaffAuthority(Integer staffId) {
                if (cause instanceof MicroException) {
                    MicroException exception = (MicroException) cause;
                    throw exception;
                } else {
                    throw new RuntimeException(cause);
                }
            }
        };

    }

}