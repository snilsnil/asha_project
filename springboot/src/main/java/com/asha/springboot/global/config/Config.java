package com.asha.springboot.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * CORS 설정
 * </p>
 * 
 * <p>
 * ObjectMapper 설정
 * </p>
 * 
 */
@Configuration
@EnableWebSecurity
public class Config implements WebMvcConfigurer {

    @Value("${spring.cors.url}") // application.properties에서 값을 주입
    private String corsUrl;

    public void addCorsMappings(CorsRegistry registry) {
        // 모든 경로에 대해 CORS를 허용
        registry.addMapping("/**")
                .allowedOrigins(corsUrl) // 허용할 프론트엔드 주소 (예: Svelte 앱)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키 전달 허용 (필요한 경우)
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}
