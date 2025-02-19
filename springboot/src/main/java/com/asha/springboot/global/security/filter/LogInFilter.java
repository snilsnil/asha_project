package com.asha.springboot.global.security.filter;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.asha.springboot.global.security.jwt.JWTGenerator;
import com.asha.springboot.global.security.userDetail.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // 클라이언트 요청에서 username, password 추출
        String username = null;
        String password = null;

        try {
            BufferedReader reader = request.getReader(); // 요청에서 body를 읽어옴
            StringBuilder sb = new StringBuilder(); // body를 저장할 StringBuilder 객체 생성
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String body = sb.toString();

            ObjectMapper mapper = new ObjectMapper();
            LoginRequest loginRequest = mapper.readValue(body, LoginRequest.class); // body를 LoginRequest 객체로 변환

            username = loginRequest.getUsername();
            password = loginRequest.getPassword();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                password, null);

        // token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authentication) {

        // CustomUserDetails 객체로 변환
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        String role = customUserDetails.getRole();

        // 60 * 60 * 10L = 10시간
        String token = jwtGenerator.createJwt(username, 60 * 60 * 10L, role);

        response.addHeader("Authorization", "Bearer " + token);
    }

    // 로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) {

        // 로그인 실패시 403 응답 코드 반환
        response.setStatus(403);
    }

    private static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
