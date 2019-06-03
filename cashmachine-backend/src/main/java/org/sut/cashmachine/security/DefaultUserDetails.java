package org.sut.cashmachine.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.sut.cashmachine.model.user.UserModel;

import java.util.Collection;

public class DefaultUserDetails implements UserDetails {

    private UserModel user;

    public DefaultUserDetails(UserModel user) {
        this.user = user;
    }

    public static DefaultUserDetails of(UserModel user) {
        return new DefaultUserDetails(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getActive();
    }

    @Override
    public boolean isEnabled() {
        return user.getActive();
    }
}
