package com.example.mongodbproject.security.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(ApplicationClients.class)
public class AuthenticationManagerConfig extends
        GlobalAuthenticationConfigurerAdapter {

    @Autowired
    ApplicationClients application;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        for (ApplicationClient client : application.getClients ()) {
            auth.inMemoryAuthentication ()
                    .withUser (client.getUsername ()).password (client.getPassword ()).roles (client.getRoles ());
        }
    }

}