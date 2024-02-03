package com.springboot.blog.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.blog.Models.User;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken{
    
    private final User user;

    public UserPrincipalAuthenticationToken(User user){
        super(user.getAuthorities());
        this.user=user;
        setAuthenticated(true);
    }
    
    @Override
    public UserDetails getCredentials() {
        return user;
    }

    @Override
    public UserDetails getPrincipal() {
       return user;
    }
    
}
