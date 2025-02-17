package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * 로그인에 관한 DTO
 * 
 * @param username
 * @param password
 * 
 * 
 */
@Getter
public class LogInDTO {
    private String username;
    private String password;

    /**
     * default 생성자
     *
     * 
     * @param username
     * @param password
     */
    @Builder
    public LogInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .build(); // entity 객체 생성
    }
}
