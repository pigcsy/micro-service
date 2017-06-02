package com.auth.security.client;

import com.auth.provider.client.AuthorizationClient;
import com.auth.provider.domain.system.OauthSystemVo;
import com.common.support.enums.SystemEn;
import com.core.support.security.CustomClientAuthentication;
import com.core.support.security.DefaultCurrentPrincipal;
import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Collection;
import java.util.List;

public class CustomClientCredentialsTokenGranter extends ClientCredentialsTokenGranter {


    AuthorizationClient authorizationClient;
    // AuthorizationClientTest authorizationClient;

    private GrantedAuthoritiesMapper authoritiesMapper = new SimpleAuthorityMapper();

    protected CustomClientCredentialsTokenGranter(AuthorizationServerTokenServices tokenServices,
                                                  ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    public CustomClientCredentialsTokenGranter(AuthorizationServerTokenServices tokenServices,
                                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, AuthorizationClient authorizationClient) {
        super(tokenServices, clientDetailsService, requestFactory);
        this.authorizationClient = authorizationClient;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        // 合作方信息
        OauthSystemVo oauthSystemVo = authorizationClient.getOauthSystem(client.getClientId());

        DefaultCurrentPrincipal principal = new DefaultCurrentPrincipal(oauthSystemVo.getSystemId(),
                oauthSystemVo.getSystemName(), oauthSystemVo.getSystemId(), oauthSystemVo.getSystemType(),
                oauthSystemVo.getDailyAccessNum(), oauthSystemVo.getMinuteAccessNum());
        // 以系统对接不设置角色，默认clientId为角色
        SystemEn systemEn = SystemEn.toEnum(oauthSystemVo.getSystemType());
        List<GrantedAuthority> authorityList = Lists.newArrayList(new SimpleGrantedAuthority(systemEn.getRolePrefix() + oauthSystemVo.getSystemId()));
        Collection<? extends GrantedAuthority> authorities = authoritiesMapper.mapAuthorities(authorityList);
        Authentication userAuth = new CustomClientAuthentication(principal, client.getClientId(), authorities);
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

}
