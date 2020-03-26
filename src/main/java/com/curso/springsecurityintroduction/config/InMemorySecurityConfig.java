package com.curso.springsecurityintroduction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/*Necessário comentar linha abaixo para utilizar outras configurações de segurança*/
//@Configuration
public class InMemorySecurityConfig {

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("bruno").password("{noop}123").roles("LIST_USERS", "LIST_PRODUCTS")
            .and()
                .withUser("davi").password("{noop}123").roles("LIST_COLORS");
    }
}
