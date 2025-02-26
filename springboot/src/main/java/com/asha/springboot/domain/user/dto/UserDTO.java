package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.entity.UserRole;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * UserEnity와 상호작용하기 위한 DTO
 * 
 * @param userId
 * @param username
 * @param password
 * @param role
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private UserRole role;

    /**
     * 회원가입 DTO 생성자 (username, password, role)
     * 
     * @param username
     * @param password
     * @param role
     */
    @Builder
    public UserDTO(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * UserDTO를 UserEntity로 변환
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
