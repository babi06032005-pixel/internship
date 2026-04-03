package com.bookmate.config;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwtUtil {
    @Value("${bookmate.jwt.secret}") private String secret;
    @Value("${bookmate.jwt.expiration}") private long expiration;
    private SecretKey key() { return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }
    public String generateToken(String email, Long userId) {
        return Jwts.builder().subject(email).claim("userId", userId)
            .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key()).compact();
    }
    public String extractEmail(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public Long extractUserId(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().get("userId", Long.class);
    }
    public boolean validateToken(String token) {
        try { Jwts.parser().verifyWith(key()).build().parseSignedClaims(token); return true; }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }
}
