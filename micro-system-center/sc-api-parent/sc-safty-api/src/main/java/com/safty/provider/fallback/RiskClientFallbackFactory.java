package com.safty.provider.fallback;

import com.core.exception.MicroException;
import com.safty.domain.request.RiskRequest;
import com.safty.domain.response.RiskResponse;
import com.safty.provider.client.RiskClient;
import feign.hystrix.FallbackFactory;


public class RiskClientFallbackFactory implements FallbackFactory<RiskClient> {

    @Override
    public RiskClient create(Throwable cause) {
        return new RiskClient() {

            @Override
            public RiskResponse riskDataTrade(RiskRequest request) {
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