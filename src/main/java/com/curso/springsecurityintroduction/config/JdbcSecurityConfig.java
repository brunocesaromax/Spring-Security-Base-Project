package com.curso.springsecurityintroduction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.curso.springsecurityintroduction.utils.JdbcUtils.*;

//@Configuration
public class JdbcSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder,
                                PasswordEncoder passwordEncoder,
                                DataSource dataSource) throws Exception {
        builder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(USER_BY_USERNAME)
                .authoritiesByUsernameQuery(PERMISSIONS_BY_USER);
//                .groupAuthoritiesByUsername(PERMISSIONS_BY_GROUP);
    }
}
