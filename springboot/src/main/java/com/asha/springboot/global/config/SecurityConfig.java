package com.asha.springboot.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 전반적인 시큐리티 설정
 * 
 * <p>
 * Spring Security중에서 인증, 인가와 CORS 설정
 * (<strong>아직까진 설정 미구현</strong>)
 * </p>
 * 
 * <p>
 * 비밀번호를 해쉬코드로 변환하기 위한 PasswordEncoder Bean 등록
 * </p>
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final AuthenticationConfiguration authenticationConfiguration;
    // private final JWTGenerator jwtGenerator;

    // // AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    // public SecurityConfig(AuthenticationConfiguration
    // authenticationConfiguration, JWTGenerator jwtGenerator) {
    // this.authenticationConfiguration = authenticationConfiguration;
    // this.jwtGenerator = jwtGenerator;
    // }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .csrf(AbstractHttpConfigurer::disable)
    // .cors((corsCustomizer -> corsCustomizer.configurationSource(new
    // CorsConfigurationSource() {

    // @Override
    // public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

    // CorsConfiguration configuration = new CorsConfiguration();

    // configuration.setAllowedOrigins(Collections.singletonList("http://127.0.0.1:5173"));
    // configuration.setAllowedMethods(Collections.singletonList("*"));
    // configuration.setAllowCredentials(true);
    // configuration.setAllowedHeaders(Collections.singletonList("*"));
    // configuration.setMaxAge(3600L);

    // configuration.setExposedHeaders(Collections.singletonList("Authorization"));

    // return configuration;
    // }
    // })))
    // .sessionManagement(sess -> sess
    // .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // .authorizeHttpRequests((auth) -> auth
    // .requestMatchers("/login", "/checkToken", "/signup").permitAll() // /signup
    // 엔드포인트에 대해 시큐리티 필터
    // // 비활성화
    // .anyRequest().authenticated())
    // .addFilterBefore(new JwtFilter(jwtGenerator),
    // UsernamePasswordAuthenticationFilter.class) // JwtFilter를
    // // UsernamePasswordAuthenticationFilter
    // // 앞에 추가
    // .addFilterAt(new
    // LoginFilter(authenticationManager(authenticationConfiguration),
    // jwtGenerator),
    // UsernamePasswordAuthenticationFilter.class);
    // return http.build();
    // }

    // // AuthenticationManager Bean 등록
    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration configuration) throws
    // Exception {

    // return configuration.getAuthenticationManager();
    // }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
