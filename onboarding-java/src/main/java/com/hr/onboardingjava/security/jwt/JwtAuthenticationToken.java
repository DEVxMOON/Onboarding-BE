package com.hr.onboardingjava.security.jwt;

import com.hr.onboardingjava.security.UserPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal principal;
    private final Object details;

    public JwtAuthenticationToken(UserPrincipal principal, WebAuthenticationDetails details) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.details = details;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}