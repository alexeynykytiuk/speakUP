package com.example.speakup.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean hasAuthority(String name){
        for (GrantedAuthority authority : getUser().getAuthorities()) {
            if(name.equals(authority.getAuthority())){
                return true;
            }
        }
        return false;
    }

    public String getUserName(){
        return getUser().getUsername();
    }

    private CustomUserDetails getUser(){
        return (CustomUserDetails) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication(){
        SecurityContext context = SecurityContextHolder.getContext();

        return context.getAuthentication();
    }
}
