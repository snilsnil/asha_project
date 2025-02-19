package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;

import lombok.AllArgsConstructor;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
