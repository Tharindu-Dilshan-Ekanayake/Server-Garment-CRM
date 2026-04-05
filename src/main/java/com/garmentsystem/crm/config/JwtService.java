package com.garmentsystem.crm.config;

import com.garmentsystem.crm.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 🔐 Generate Token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("phone", user.getPhone())
                .claim("role", user.getRole())
                .claim("position", user.getPosition())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Extract ALL claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 📧 Extract email
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    // 🆔 Extract userId
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    // 👤 Extract name
    public String extractName(String token) {
        return extractAllClaims(token).get("name", String.class);
    }

    // 📱 Extract phone
    public String extractPhone(String token) {
        return extractAllClaims(token).get("phone", String.class);
    }

    // extract role
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // extract position
    public String extractPosition(String token) {
        return extractAllClaims(token).get("position", String.class);
    }

    // ⏳ Check token expired
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}