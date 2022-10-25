package com.felix.felixapis.security;

import com.felix.felixapis.model.FelixUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private FelixUser felixUser;

    public UserDetailsImpl(FelixUser felixUser) {
        super();
        this.felixUser = felixUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(felixUser.getRole()));
    }

    @Override
    public String getPassword() {
        return felixUser.getPassword();
    }

    @Override
    public String getUsername() {
        return felixUser.getUsername();
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
        return true;
    }
}
