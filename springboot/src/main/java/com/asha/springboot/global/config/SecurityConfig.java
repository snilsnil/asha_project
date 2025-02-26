package com.asha.springboot.global.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.asha.springboot.domain.user.repository.UserRepository;
import com.asha.springboot.global.security.filter.JwtFilter;
import com.asha.springboot.global.security.filter.LogInFilter;
import com.asha.springboot.global.security.jwt.JWTGenerator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * 전반적인 시큐리티 설정
 * 
 * <p>
 * Spring Security중에서 인증, 인가와 CORS 설정
 * </p>
 * 
 * <p>
 * 비밀번호를 해쉬코드로 변환하기 위한 PasswordEncoder Bean 등록
 * </p>
 * 
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTGenerator jwtGenerator;
    private final UserRepository userRepository;

    // AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    // public SecurityConfig(AuthenticationConfiguration
    // authenticationConfiguration, JWTGenerator jwtGenerator) {
    // this.authenticationConfiguration = authenticationConfiguration;
    // this.jwtGenerator = jwtGenerator;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) { // CORS 설정

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(
                                Collections.singletonList("http://localhost:3000")); // 허용할
                        // 프론트엔드 주소
                        configuration.setAllowedMethods(Collections.singletonList("*")); // 허용할 HTTP 메소드
                        configuration.setAllowCredentials(true); // 쿠키 전달 허용
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
                        configuration.setMaxAge(3600L); // 1시간 동안 캐싱

                        configuration.setExposedHeaders(Collections.singletonList("Authorization")); // 노출할 헤더

                        return configuration;
                    }
                })))
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/signup")
                        .permitAll() // /signup 엔드포인트에 대해 시큐리티 필터
                                     // 비활성화
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtFilter(jwtGenerator, userRepository),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(
                        new LogInFilter(authenticationManager(authenticationConfiguration), jwtGenerator,
                                userRepository),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    // PasswordEncoder Bean 등록
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
