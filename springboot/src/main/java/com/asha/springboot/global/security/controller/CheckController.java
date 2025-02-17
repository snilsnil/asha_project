package com.asha.springboot.global.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asha.springboot.global.security.jwt.JWTGenerator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@ResponseBody

/**
 * 토큰 검증 컨트롤러
 * 
 * <p>
 * 클라이언트에서 전달받은 토큰을 검증하는 컨트롤러
 * </p>
 * 
 */
public class CheckController {

    private final JWTGenerator jwtGenerator;

    @GetMapping("/checkToken")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");

        if (jwtGenerator.isExpired(token)) {
            return ResponseEntity.status(401).body("Token expired");
        }

        return ResponseEntity.ok().body("Token valid");
    }
}
