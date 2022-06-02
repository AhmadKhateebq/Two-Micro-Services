package com.example.mongodbproject.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class BeansManager {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate ();
    }
}
