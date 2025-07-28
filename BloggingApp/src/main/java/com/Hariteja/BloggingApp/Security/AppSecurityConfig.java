package com.Hariteja.BloggingApp.Security;


import com.Hariteja.BloggingApp.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig{

    JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JWTService jwtService;
    private final UserService userService;

    public AppSecurityConfig( JWTService jwtService, UserService userService){
        this.jwtService=jwtService;
        this.userService=userService;

    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        return new JwtAuthenticationFilter(
                new JwtAuthenticationManager(jwtService, userService)
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer -> Customizer.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users/login","/articles").permitAll()
                        .requestMatchers("/users/signup", "/users", "/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .addFilterBefore(jwtAuthenticationFilter(), AnonymousAuthenticationFilter.class);
        return http.build();
    }



}
