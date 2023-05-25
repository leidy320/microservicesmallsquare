package com.pragma.powerup.usermicroservice.configuration.security;


import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.ITokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityManager {
    @Bean
    public JwtTokenFilter jwtTokenFilter() {return new JwtTokenFilter();}
    @Bean
    public ITokenUtils tokenUtils() {return new TokenUtilsImpl();}

}