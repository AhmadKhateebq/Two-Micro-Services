package com.example.mongodbproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class Basic extends WebSecurityConfigurerAdapter {

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf ().disable ().httpBasic ().and ()
                .requestMatchers ().antMatchers ("/course/c/**").and ()
                .authorizeRequests ()
//                .antMatchers ("/course/c/**").hasRole ("USER")
                .anyRequest ().hasRole ("USER")

        ;
    }

        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication ()
                .passwordEncoder (passwordEncoder)
                .withUser ("user")
                .password ("user")
                .roles ("USER")
                .and ()
                .withUser ("admin")
                .password ("admin")
                .roles ("ADMIN")
        ;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance ();
    }

}