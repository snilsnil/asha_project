package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 로그인에 관한 DTO
 * 
 * @param username
 * @param password
 * 
 * 
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    /**
     * LogInDTO에 있는 정보를 UserEntity로 변환환
     * 
     * @return UserEntity
     */
    public UserEntity toEntity() {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .build(); // entity 객체 생성
    }
}
