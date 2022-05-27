package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class securiteConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .mvcMatchers("/", "/login", "/logout",
                        "/inscription").permitAll()
                /*.mvcMatchers("/dev/*").hasRole("DEV")
                .mvcMatchers("/client/*").hasRole("CLIENT")
                .mvcMatchers("/admin/*").hasRole("ADMIN")*/
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout()
                .clearAuthentication(true).invalidateHttpSession(true)
                .and()
                .csrf().disable();
    }
}