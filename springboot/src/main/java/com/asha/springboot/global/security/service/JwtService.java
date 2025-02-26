package com.asha.springboot.global.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.repository.UserRepository;
import com.asha.springboot.global.security.jwt.JWTGenerator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final JWTGenerator jwtGenerator;
    private final UserRepository userRepository;

    public List<String> remakeToken(String refreshToken) {

        Long userId = Long.valueOf(jwtGenerator.getUserId(refreshToken));

        UserEntity userEntity = userRepository.findByUserId(userId);

        String username = userEntity.getUsername();
        String role = userEntity.getRole().name();

        Long tewntyMinutes = 60 * 20L;
        Long tenHours = 60 * 60 * 10L;

        String remakeAccessToken = jwtGenerator.createAccessJwt(userId, username, tewntyMinutes, role);

        String remakeRefreshToken = jwtGenerator.createRefreshJwt(userId, tenHours);

        return List.of(remakeAccessToken, remakeRefreshToken);
    }

}
