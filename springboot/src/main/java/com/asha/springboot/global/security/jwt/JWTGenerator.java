package com.asha.springboot.global.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

/**
 * JWT 생성기
 * 
 * <p>
 * JWT를 생성하고, 검증하는 기능을 제공
 * </p>
 * 
 */
@Component
public class JWTGenerator {

    private SecretKey secretKey;

    public JWTGenerator(@Value("${spring.jwt.secret}") String secret) { // application.properties에 저장된 secret key를 가져옴

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), // secret key를 byte 배열로 변환
                Jwts.SIG.HS256.key().build().getAlgorithm()); // secret key를 SecretKey 객체로 변환
    }

    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username",
                String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role",
                String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration()
                .before(new Date());
    }

    public String createJwt(String username, Long expiredMs, String role) {

        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

}
