package com.example.TwoSqlDB.controller;

import com.example.TwoSqlDB.security.model.AuthenticationRequest;
import com.example.TwoSqlDB.security.model.AuthenticationResponse;
import com.example.TwoSqlDB.security.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService service;
    @Autowired
    private jwtUtil util;
    @PostMapping("authenticate")
    public ResponseEntity<?> auth(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken (request.getUsername (), request.getPassword ())
            );
        } catch (BadCredentialsException e) {
            throw new Exception ("Incorrect Login");
        }
        final UserDetails details = service.loadUserByUsername (request.getUsername ());
        final String jwt = util.generateToken (details);
        return ResponseEntity.ok (new AuthenticationResponse (jwt));
    }
}
