package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * UserEnity와 상호작용하기 위한 DTO
 * 
 * @param userId
 * @param username
 * @param password
 * @param role
 */
@Getter
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private String role;

    /**
     * 회원가입 DTO 생성자 (userId, username, password, role)
     * 
     * @param userId
     * @param username
     * @param password
     * @param role
     */
    @Builder // 생성자 대신 빌더 사용 (변경 가능성을 최소화) -> 생성자 패턴 중 빌더 패턴
    public UserDTO(Long userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * 회원가입 DTO 생성자 (username, password, role)
     * 
     * @param username
     * @param password
     * @param role
     */
    @Builder
    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * DB와 사용하기 위해 UserEntity로 변환
     * 
     * @return UserEntity
     */
    public UserEntity toEntity() {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .role(role)
                .build(); // entity 객체 생성
    }
}
