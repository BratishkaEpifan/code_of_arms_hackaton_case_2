package com.example.code_of_arms_hackaton_case_2.security;

import com.example.code_of_arms_hackaton_case_2.entity.BankUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    static CustomUserDetails fromUserEntityToCustomUserDetails(BankUser bankUser) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        if (bankUser != null) {
            customUserDetails.login = bankUser.getLogin();
            customUserDetails.password = bankUser.getPassword();
            customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(
                    bankUser.getRole()));
        }
        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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