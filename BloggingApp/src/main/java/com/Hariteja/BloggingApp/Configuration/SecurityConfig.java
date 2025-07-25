package com.Hariteja.BloggingApp.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(Customizer -> Customizer.disable())
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                       .requestMatchers("/users/signup","/users","/h2-console/**").permitAll()
                       .anyRequest().authenticated())
               //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }

}
