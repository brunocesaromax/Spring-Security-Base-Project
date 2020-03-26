package com.curso.springsecurityintroduction.config;

import com.curso.springsecurityintroduction.security.UserSessionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder,
                                PasswordEncoder passwordEncoder,
                                UserSessionDetailsService userSessionDetailsService) throws Exception {
        builder
                .userDetailsService(userSessionDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
