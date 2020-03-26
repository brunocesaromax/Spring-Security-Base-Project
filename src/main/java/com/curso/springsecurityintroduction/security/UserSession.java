package com.curso.springsecurityintroduction.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserSession implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private String name;
    private String username;
    private String password;
    private boolean active;
    private Collection<GrantedAuthority> permissions = new ArrayList<>();

    public UserSession(String name, String username, String password, boolean active) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
