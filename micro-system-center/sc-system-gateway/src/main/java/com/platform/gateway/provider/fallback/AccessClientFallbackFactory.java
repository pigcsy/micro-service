package com.platform.gateway.provider.fallback;

import com.core.exception.MicroException;
import com.platform.gateway.provider.client.AccessClient;
import com.platform.gateway.provider.request.AccessRequestVo;
import feign.hystrix.FallbackFactory;


public class AccessClientFallbackFactory implements FallbackFactory<AccessClient> {

	@Override
	public AccessClient create(Throwable throwable) {
		return new AccessClient() {
			@Override
			public String checkAccessLimit(AccessRequestVo request) {
				throw (throwable instanceof MicroException) ? (MicroException) throwable : new RuntimeException(throwable);
			}

			@Override
			public String increment(AccessRequestVo request) {
				throw (throwable instanceof MicroException) ? (MicroException) throwable : new RuntimeException(throwable);
			}
		};
	}

}
