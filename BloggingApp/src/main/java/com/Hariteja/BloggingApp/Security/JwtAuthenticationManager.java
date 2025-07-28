package com.Hariteja.BloggingApp.Security;

import com.Hariteja.BloggingApp.users.UserEntity;
import com.Hariteja.BloggingApp.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtAuthenticationManager implements AuthenticationManager {

    private final JWTService jwtService;
    private final UserService userService;
    JwtAuthenticationManager(JWTService jwtService, UserService userService){
        this.jwtService=jwtService;
        this.userService=userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if(authentication instanceof JWTAuthentication){
                var jwtAuthentication = (JWTAuthentication) authentication;
                var jwt= jwtAuthentication.getCredentials();
                var userId = jwtService.retrieveUserId(jwt);
                jwtAuthentication.userEntity= userService.getUser(userId);
                jwtAuthentication.setAuthenticated(true);



                return jwtAuthentication;
            }

        throw new IllegalAccessError("cant access with non jwt authentication");
    }
}
