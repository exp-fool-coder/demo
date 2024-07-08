package com.example.demo.manager.authorization;

import com.example.demo.model.ClaimField;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JWTAuthentication implements Authentication {

    private boolean authenticated;
    private final String login;
    private final int userId;
   // private Set<Role> roles;


    public JWTAuthentication(Claims claims) {
        this.login = (String) claims.get(ClaimField.LOGIN.getFieldName());
        this.userId = Integer.parseInt((String) claims.get(ClaimField.USER_ID.getFieldName()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return login;
    }
}
