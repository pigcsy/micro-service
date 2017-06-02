package com.auth.security.password;

import com.auth.provider.client.AuthorizationClient;
import com.auth.provider.domain.system.AdminStaffVo;
import com.auth.provider.domain.system.OauthSystemVo;
import com.core.exception.LogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;

@Component
public class CustomUserDetailService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    AuthorizationClient authorizationClient;

    public CustomUserDetailService() {
    }

    // AuthorizationClientTest authorizationClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("未找到用户");
    }

    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(String username, Authentication authentication)
            throws UsernameNotFoundException {
        logger.info("用户权限请求,username={}", username);
        ;
        try {
            String clientId = ((Map<String, Object>) authentication.getDetails()).get("client_id").toString();
            AdminStaffVo staff = authorizationClient.getAdminStaff(username);
            OauthSystemVo oauthSystemVo = authorizationClient.getOauthSystem(clientId);
            if (staff.getSystemId() != oauthSystemVo.getSystemId().intValue()) {
                throw new LogicException("未找到用户");
            }
            return new User(username, staff.getPwd(), staff.getStatus() != 0, true, true, true,
                    new ArrayList<GrantedAuthority>());
        } catch (Exception e) {
            logger.error("未找到用户->{}", username, e);
            throw new UsernameNotFoundException("用户不存", e);
        }

    }

}
