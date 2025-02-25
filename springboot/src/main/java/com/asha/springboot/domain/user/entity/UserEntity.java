package com.asha.springboot.domain.user.entity;

import com.asha.springboot.domain.user.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 사용자 ID 와 비밀번호, 분류를 위한 엔티티
 */
@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id // primary key(기본키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 index 증가
    private Long userId;

    @Column(name = "username", unique = true) // username은 unique(단일 조건)해야 함
    private String username; // 사용자 ID

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public UserEntity(Long userId, String username, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * UserDTO로 변환
     * 
     * @return
     */
    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .password(password)
                .role(role)
                .build(); // entity 객체 생성
    }
}
