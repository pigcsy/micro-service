package com.auth.security;

import com.core.support.security.DefaultCurrentPrincipal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomUser extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private DefaultCurrentPrincipal currentUser;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        // TODO Auto-generated constructor stub
    }

}
