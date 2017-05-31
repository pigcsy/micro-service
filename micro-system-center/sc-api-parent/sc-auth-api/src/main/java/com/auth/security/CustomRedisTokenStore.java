package com.auth.security;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

public class CustomRedisTokenStore extends RedisTokenStore {

	public CustomRedisTokenStore(RedisConnectionFactory connectionFactory) {
		super(connectionFactory);
		// TODO Auto-generated constructor stub
	}

}
