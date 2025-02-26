package com.asha.springboot.global.security.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.repository.UserRepository;
import com.asha.springboot.global.security.jwt.JWTGenerator;
import com.asha.springboot.global.security.service.JwtService;

import jakarta.servlet.http.HttpServletResponse;
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
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @GetMapping("/accessToken")
    public ResponseEntity<?> accessToken(@RequestHeader("Authorization") String authorization) {
        String accessToken = authorization.replace("Bearer ", "");

        if (jwtGenerator.isExpired(accessToken)) {
            return ResponseEntity.status(401).body("Token expired");
        }

        String username = jwtGenerator.getUsername(accessToken);

        UserEntity userEntity = userRepository.findByUsername(username);

        return ResponseEntity.ok().body(userEntity);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authorization,
            HttpServletResponse response) {
        String refreshToken = authorization.replace("Bearer ", "");
        if (jwtGenerator.isExpired(refreshToken)) {
            return ResponseEntity.status(401).body("Token expired");
        }

        List<String> result = jwtService.remakeToken(refreshToken);
        String accessToken = result.get(0);
        String newRefreshToken = result.get(1);

        Map<String, String> token = Map.of(
                "accessToken", accessToken,
                "refreshToken", newRefreshToken);

        // 상태 코드 200 OK와 함께 응답 본문을 반환
        return ResponseEntity.ok().body(token);
    }

}
