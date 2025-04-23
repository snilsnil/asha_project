package com.asha.springboot.global.security.filter;

import java.io.IOException;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.repository.UserRepository;
import com.asha.springboot.global.security.jwt.JWTGenerator;
import com.asha.springboot.global.security.userDetail.CustomUserDetails;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * 로그인 필터
 * 
 * <p>
 * 클라이언트에서 전달받은 username, password를 검증하고, 검증이 성공하면 JWT를 발급
 * </p>
 * 
 */
@Builder
@AllArgsConstructor
public class LogInFilter extends UsernamePasswordAuthenticationFilter {

        private final AuthenticationManager authenticationManager;
        private final JWTGenerator jwtGenerator;
        private final UserRepository userRepository;

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                        throws AuthenticationException {

                // 클라이언트 요청에서 username, password 추출
                String username = obtainUsername(request);
                String password = obtainPassword(request);

                // 스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                                password, null);

                // token에 담은 검증을 위한 AuthenticationManager로 전달
                return authenticationManager.authenticate(authToken);
        }

        // 로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain, Authentication authentication)
                        throws StreamWriteException, DatabindException, IOException {

                // CustomUserDetails 객체로 변환
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

                String username = customUserDetails.getUsername();

                UserEntity userEntity = userRepository.findByUsername(username);
                String role = userEntity.getRole().name();
                Long userId = userEntity.getUserId();

                Long tewntyMinutes = 60 * 20L;
                Long tenHours = 60 * 60 * 10L;

                // 60 * 60 * 10L = 10시간
                String accessToken = jwtGenerator.createAccessJwt(userId, username, tewntyMinutes, role);

                String refreshToken = jwtGenerator.createRefreshJwt(userId, tenHours);

                ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                                .maxAge(10 * 60 * 60)
                                .path("/")
                                .httpOnly(true)
                                .sameSite("Lax")
                                .build();

                ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", accessToken)
                                .maxAge(25 * 60)
                                .path("/")
                                .httpOnly(true)
                                .sameSite("Lax")
                                .build();

                response.addHeader("Set-Cookie", refreshTokenCookie.toString());
                response.addHeader("Set-Cookie", accessTokenCookie.toString());

        }

        // 로그인 실패시 실행하는 메소드
        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException failed) {

                // 로그인 실패시 403 응답 코드 반환
                response.setStatus(403);
        }
}
