package com.example.mongodbproject.security.service;

import com.example.mongodbproject.security.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    jwtUtil util;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User ("user", "user", new ArrayList<> ());
        System.out.println (user.getUsername () + " " + user.getPassword ());
        System.out.println (util.generateToken (user));
        System.out.println (util.validateToken (util.generateToken (user), user));
        return user;
    }
}
