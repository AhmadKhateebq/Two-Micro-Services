package com.example.TwoSqlDB.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class jwtUtil {
    private final String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim (token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim (token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims (token);
        return claimsResolver.apply (claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser ().setSigningKey (SECRET_KEY).parseClaimsJws (token).getBody ();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration (token).before (new Date ());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<> ();
        return createToken (claims, userDetails.getUsername ());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        JwtBuilder jwts = Jwts.builder ();
        jwts.setClaims (claims);
        jwts.setSubject (subject);
        jwts.setIssuedAt (new Date (System.currentTimeMillis ()));
        jwts.setExpiration (new Date (System.currentTimeMillis () + 1000 * 60 * 60 * 10));
        jwts.signWith (SignatureAlgorithm.HS512, SECRET_KEY);
        System.out.println ("done");
        return jwts.compact ();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername (token);
        return (username.equals (userDetails.getUsername ()) && !isTokenExpired (token));
    }

    public String generateToken(Map<String, Object> claims, String string) {
        return createToken (claims, string);
    }

}