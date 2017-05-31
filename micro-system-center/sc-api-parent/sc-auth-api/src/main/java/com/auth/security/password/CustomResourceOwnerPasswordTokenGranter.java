package com.auth.security.password;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

public class CustomResourceOwnerPasswordTokenGranter extends ResourceOwnerPasswordTokenGranter {
	
	AuthenticationManager authenticationManager;
	protected CustomResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory, String grantType) {
		super(authenticationManager, tokenServices, clientDetailsService, requestFactory, grantType);
		this.authenticationManager = authenticationManager;	
		// TODO Auto-generated constructor stub
	}

	public CustomResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory) {
		super(authenticationManager, tokenServices, clientDetailsService, requestFactory);
		this.authenticationManager = authenticationManager;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
		// TODO Auto-generated method stub
		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
		String username = parameters.get("username");
		String password = parameters.get("password");
		parameters.put("client_id", client.getClientId());
		// Protect from downstream leaks of password
		parameters.remove("password");
		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
		try {
			userAuth = authenticationManager.authenticate(userAuth);
		} catch (AccountStatusException ase) {
			// covers expired, locked, disabled cases (mentioned in section 5.2,
			// draft 31)
			throw new InvalidGrantException(ase.getMessage());
		} catch (BadCredentialsException e) {
			// If the username/password are wrong the spec says we should send
			// 400/invalid grant
			throw e;//new InvalidGrantException(e.getMessage());
		}
		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + username);
		}

		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		return new OAuth2Authentication(storedOAuth2Request, userAuth);
	}

}
