package com.asha.springboot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

/**
 * 사용자 ID 와 비밀번호, 분류를 위한 엔티티
 */
@Getter
@Entity
public class UserEntity {

    @Id // primary key(기본키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 index 증가
    private Long userId;

    @Column(name = "username", unique = true) // username은 unique(단일 조건)해야 함
    private String username; // 사용자 ID

    private String password;

    private String role;

    @OneToOne(mappedBy = "userEntity") // 1:1 양방향을 위해 UserInfoEntity의 userEntity를 참조 (외래키)
    private UserInfoEntity userInfoEntity;

    @OneToOne(mappedBy = "userEntity") // 1:1 양방향을 위해 UserInfoEntity의 userEntity를 참조 (외래키)
    private UserNickNameEntity userNickNameEntity;

    @Builder // 생성자 대신 빌더 사용 (변경 가능성을 최소화) -> 생성자 패턴 중 빌더 패턴
    public UserEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
