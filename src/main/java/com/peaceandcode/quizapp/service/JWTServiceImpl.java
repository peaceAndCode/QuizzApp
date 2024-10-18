package com.peaceandcode.quizapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    private final String secretKey;
    private final long expirationTimeInMinutes;

    public JWTServiceImpl(
        @Value("${jwt.secret.key}") String secretKey,
        @Value("${jwt.expiration.minutes}") long expirationTimeInMinutes
    ){
        this.secretKey = secretKey;
        this.expirationTimeInMinutes = expirationTimeInMinutes;
    }

    @Override
    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return new SecretKeySpec(key.getEncoded(), key.getAlgorithm());
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
        .parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }

    @Override
    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        long millisecondsInAMinute = 60L * 1000L;
        Date expiration = new Date(System.currentTimeMillis() + expirationTimeInMinutes * millisecondsInAMinute);

        return Jwts
        .builder()
        .claims(extraClaims)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(expiration)
        .subject(userDetails.getUsername())
        .signWith(getSigningKey())
        .compact();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    @Override
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
