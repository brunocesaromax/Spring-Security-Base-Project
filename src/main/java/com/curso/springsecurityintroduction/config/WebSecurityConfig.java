package com.curso.springsecurityintroduction.config;

import com.curso.springsecurityintroduction.security.UserSessionDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSessionDetailsService userSessionDetailsService;

    public WebSecurityConfig(UserSessionDetailsService userSessionDetailsService) {
        this.userSessionDetailsService = userSessionDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/users").hasAnyRole("LIST_USERS")
                    .antMatchers("/products").hasAnyRole("LIST_PRODUCTS")
                    .antMatchers("/colors").hasAnyRole("LIST_COLORS")
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                .and()
                .rememberMe()
                /*Usuário fecha o browser e abre de novo, necessário essa configuração para carregar permissões novamente*/
                    .userDetailsService(userSessionDetailsService);

        http.csrf()
                .ignoringAntMatchers("/h2/**");
        http.headers()
                .frameOptions()
                .sameOrigin(); //Necessário para acessar o H2 no navegador
    }
}
