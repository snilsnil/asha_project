package com.asha.springboot.global.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import com.asha.springboot.domain.user.dto.UserDTO;
import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.entity.UserRole;
import com.asha.springboot.domain.user.repository.UserRepository;
import com.asha.springboot.global.security.jwt.JWTGenerator;
import com.asha.springboot.global.security.userDetail.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    private final JWTGenerator jwtGenerator;
    private final UserRepository userRepository;

    public JwtFilter(JWTGenerator jwtGenerator, UserRepository userRepository) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Boolean path = checkPath(request.getRequestURI());
        System.out.println(path);

        if (path) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = null;
        String refreshToken = null;

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.replace("Bearer ", "");
            if (jwtGenerator.isAccessToken(token)) {
                accessToken = token;
            } else if (jwtGenerator.isRefreshToken(token)) {
                refreshToken = token;
            }
        }

        if (accessToken != null && !jwtGenerator.isExpired(accessToken)) {
            processAccessToken(accessToken);
        } else if (refreshToken != null && !jwtGenerator.isExpired(refreshToken)) {
            processRefreshToken(refreshToken);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void processAccessToken(String accessToken) {
        String username = jwtGenerator.getUsername(accessToken);
        UserRole role = UserRole.valueOf(jwtGenerator.getRole(accessToken));

        UserDTO userDTO = new UserDTO(username, "temppassword", role);
        CustomUserDetails customUserDetails = new CustomUserDetails(userDTO);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private void processRefreshToken(String refreshToken) {
        Long userId = Long.valueOf(jwtGenerator.getUserId(refreshToken));
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDTO userDTO = userEntity.toDTO();
        CustomUserDetails customUserDetails = new CustomUserDetails(userDTO);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private Boolean checkPath(String path) {
        if ("/signup".equals(path) || "/login".equals(path) || path.startsWith("/products")) {
            return true;
        }
        return false;
    }
}
