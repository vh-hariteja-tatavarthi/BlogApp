package com.Hariteja.BloggingApp.Security;

import com.Hariteja.BloggingApp.users.UserEntity;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.List;

public class JWTAuthentication implements Authentication {
    String jwt;
    UserEntity userEntity;

    public JWTAuthentication(String jwt) {
        this.jwt=jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserEntity getPrincipal() {
        return userEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return (userEntity!=null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "";
    }
    @Override
    public boolean implies(Subject subject){
        return Authentication.super.implies(subject);
    }
}
