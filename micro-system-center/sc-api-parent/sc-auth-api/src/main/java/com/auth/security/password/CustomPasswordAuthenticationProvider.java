package com.auth.security.password;

import com.auth.provider.client.AuthorizationClient;
import com.auth.provider.domain.system.AdminStaffVo;
import com.auth.provider.domain.system.OauthSystemVo;
import com.common.support.enums.SystemEn;
import com.core.exception.LogicException;
import com.core.support.security.DefaultCurrentPrincipal;
import com.core.utils.AppUtils;
import com.core.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomPasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    AuthorizationClient authorizationClient;
    @Autowired
    private CustomUserDetailService userDetailsService;
    @Value("${service.rsa.privateKey}")
    private String rsaPrivateKey;

    private GrantedAuthoritiesMapper authoritiesMapper = new SimpleAuthorityMapper();

    public CustomPasswordAuthenticationProvider() {
        this.setHideUserNotFoundExceptions(false);
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        try {
            if (authentication.getCredentials() == null) {
                logger.debug("- failed: no credentials provided");
                throw new LogicException("密码不能为空");
            }
            String rawPass = authentication.getCredentials().toString();
            String md5Pwd = AppUtils.md5Encrypt(RSAUtils.decryptByPrivateKey(rawPass, rsaPrivateKey));
            // String md5Pwd = AppUtils.md5Encrypt(rawPass);
            if (!md5Pwd.equalsIgnoreCase(userDetails.getPassword())) {
                throw new LogicException("密码错误");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("密码错误", e);
        }
    }

    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser;

        try {

            loadedUser = userDetailsService.loadUserByUsername(username, authentication);
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException("未知错误", repositoryProblem);
        }
        return loadedUser;
    }

    @SuppressWarnings("unchecked")
    /**
     * 调用system 获取用户信息
     */
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
        String clientId = ((Map<String, Object>) authentication.getDetails()).get("client_id").toString();
        AdminStaffVo staff = authorizationClient.getAdminStaff(user.getUsername());
        List<String> authoritys = authorizationClient.getStaffAuthority(staff.getStaffId());
        OauthSystemVo oauthSystemVo = authorizationClient.getOauthSystem(clientId);

        DefaultCurrentPrincipal currentUser = new DefaultCurrentPrincipal(staff.getStaffId(), staff.getStaffName(),
                staff.getSystemId(), oauthSystemVo.getSystemType(), staff.getDailyAccessNum(), staff.getMinuteAccessNum());
        SystemEn systemEn = SystemEn.toEnum(oauthSystemVo.getSystemType());
        List<GrantedAuthority> authorityList = authoritys.stream()
                .map(authority -> new SimpleGrantedAuthority(systemEn.getRolePrefix() + authority))
                .collect(Collectors.toList());
        Collection<? extends GrantedAuthority> authorities = authoritiesMapper.mapAuthorities(authorityList);

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(currentUser,
                authentication.getCredentials(), authorities);
        result.setDetails(authentication.getDetails());
        return result;
    }


}
