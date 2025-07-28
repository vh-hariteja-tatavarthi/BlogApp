package com.Hariteja.BloggingApp.Security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Component;


public class JwtAuthenticationFilter extends AuthenticationFilter {

    JwtAuthenticationManager jwtAuthenticationManager;
    public JwtAuthenticationFilter(JwtAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager,new JwtAuthenticationConverter());

        this.setSuccessHandler((request, response, authentication) ->
                SecurityContextHolder.getContext().setAuthentication(authentication));
    }
}
