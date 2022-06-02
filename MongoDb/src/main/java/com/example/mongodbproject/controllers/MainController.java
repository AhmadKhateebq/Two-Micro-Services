package com.example.mongodbproject.controllers;

import com.example.mongodbproject.model.AuthUser;
import com.example.mongodbproject.model.Course;
import com.example.mongodbproject.model.Teacher;
import com.example.mongodbproject.security.model.AuthenticationRequest;
import com.example.mongodbproject.security.model.AuthenticationResponse;
import com.example.mongodbproject.security.util.jwtUtil;
import com.example.mongodbproject.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService service;
    @Autowired
    private jwtUtil util;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CourseService courseService;

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

    @GetMapping("/student")
    public List<Teacher> getUserCourses()
    {
        String uri = "http://localhost:8082/student/";
        return (restTemplate.getForObject (uri,List.class));
//        return courseService.getAllCourses ()
//                .stream ()
//                .map (c -> restTemplate.getForObject (uri, Course.class))
////                .map (c -> restTemplate.exchange
////                        (uri, HttpMethod.GET, new HttpEntity<> (authUtil.createHeaders(user.getUsername (), user.getPassword ())), Course.class).getBody ())
//                .collect (Collectors.toList ());
    }
    @GetMapping("/header")
    public Map<String,String> getHeaders(@RequestHeader Map<String,String> headers){return headers;}

}
