package server.sopt.week2.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class UserAuthentication  extends UsernamePasswordAuthenticationToken {

    public UserAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static UserAuthentication createUserAuthentication(Long memberId) {
        return new UserAuthentication(memberId, null, null);
    }
}
