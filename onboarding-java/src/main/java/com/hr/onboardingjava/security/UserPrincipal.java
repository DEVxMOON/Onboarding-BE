package com.hr.onboardingjava.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal {

    private final Long id;
    private final String username;
    private final Collection<GrantedAuthority> authorities;

    public UserPrincipal(Long id, String username, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

}
