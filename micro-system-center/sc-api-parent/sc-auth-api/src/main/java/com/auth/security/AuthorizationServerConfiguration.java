package com.auth.security;

import com.auth.provider.client.AuthorizationClient;
import com.auth.security.client.CustomClientCredentialsTokenGranter;
import com.auth.security.password.CustomResourceOwnerPasswordTokenGranter;
import com.core.exception.MicroAuthorizationException;
import com.core.exception.MicroException;
import com.core.support.security.MicroAccessDeniedHandler;
import com.core.support.security.MicroAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DataSource dataSource;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	RedisConnectionFactory connectionFactory;
	@Autowired
	AuthorizationClient authorizationClient;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
		oauthServer.accessDeniedHandler(new MicroAccessDeniedHandler())
		.authenticationEntryPoint(new MicroAuthenticationEntryPoint());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// 启用redis支持
		endpoints.authenticationManager(authenticationManager).tokenStore(new RedisTokenStore(connectionFactory));
		// 支持password ClientCredentials 两种方式
		endpoints.tokenGranter(new TokenGranter() {
			private CompositeTokenGranter delegate;

			@Override
			public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
				if (delegate == null) {
					delegate = new CompositeTokenGranter(getTokenGranters(endpoints));
				}
				return delegate.grant(grantType, tokenRequest);
			}
		});
		
		
		
		endpoints.exceptionTranslator(new WebResponseExceptionTranslator() {
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				logger.error("认证失败",e);
				MicroException exception = null;
				if(e.getCause() != null &&  e.getCause() instanceof MicroException){
					exception = (MicroException)e.getCause();
				} else {
					exception = new MicroAuthorizationException("认证失败",e);
				}
				return new ResponseEntity<OAuth2Exception>(new CustomOAuth2Exception(exception), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		});
	}

	// token生成方式
	private List<TokenGranter> getTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
		List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
		tokenGranters.add(new CustomClientCredentialsTokenGranter(endpoints.getTokenServices(),
				endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(),authorizationClient));
		tokenGranters
				.add(new CustomResourceOwnerPasswordTokenGranter(authenticationManager, endpoints.getTokenServices(),
						endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
		return tokenGranters;
	}

}