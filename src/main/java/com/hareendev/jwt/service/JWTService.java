package com.hareendev.jwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {
    private final SecretKey secretKey;

    public JWTService() {
        try{
            SecretKey k = KeyGenerator.getInstance("HmacSHA256").generateKey();
            this.secretKey = Keys.hmacShaKeyFor(k.getEncoded());
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJWTToken() {
        return Jwts.builder()
                .subject("hareenhelanjith")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*15))
                .signWith(this.secretKey)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts
                .parser()
                .verifyWith(this.secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
