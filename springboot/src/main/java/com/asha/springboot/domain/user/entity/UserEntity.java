package com.asha.springboot.domain.user.entity;

import com.asha.springboot.domain.user.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@NoArgsConstructor // 기본 생성자 추가
public class UserEntity {

    @Id // primary key(기본키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 index 증가
    private Long userId;

    @Column(name = "username", unique = true) // username은 unique(단일 조건)해야 함
    private String username; // 사용자 ID

    private String password;

    private String role;

    @Builder // 생성자 대신 빌더 사용 (변경 가능성을 최소화) -> 생성자 패턴 중 빌더 패턴
    public UserEntity(String username, String password, String role) {
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
