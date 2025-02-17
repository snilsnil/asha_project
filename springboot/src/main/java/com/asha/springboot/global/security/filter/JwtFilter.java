package com.asha.springboot.global.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import com.asha.springboot.domain.user.dto.UserDTO;
import com.asha.springboot.global.security.jwt.JWTGenerator;
import com.asha.springboot.global.security.userDetail.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT 필터
 * 
 * <p>
 * JWT 토큰을 검증하고, 토큰이 유효하면 사용자 정보를 추출하여 스프링 시큐리티 인증 토큰을 생성
 * </p>
 * 
 */
public class JwtFilter extends OncePerRequestFilter {

    private final JWTGenerator jwtGenerator;

    public JwtFilter(JWTGenerator jwtgGenerator) {

        this.jwtGenerator = jwtgGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 요청 경로 확인
        String path = request.getRequestURI();
        if ("/signup".equals(path) || "/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

            // 조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");
        // Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.replace("Bearer ", "");

        // 토큰 소멸 시간 검증
        if (jwtGenerator.isExpired(token)) {

            System.out.println("token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰에서 username과 role 획득
        String username = jwtGenerator.getUsername(token);
        String role = jwtGenerator.getRole(token);

        // User를 생성하여 값 set
        UserDTO userDTO = new UserDTO(username, "temppassword", role);

        // UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userDTO);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
