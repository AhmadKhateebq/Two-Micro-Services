package com.example.mongodbproject.security.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("application")
public class ApplicationClients {

    private final List<ApplicationClient> clients = new ArrayList<> ();

    public List<ApplicationClient> getClients() {
        return this.clients;
    }

}


