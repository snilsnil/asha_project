package com.asha.springboot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

/**
 * 사용자 ID와 비밀번호 이외의 정보를 위한 엔티티
 */
@Getter
@Entity
public class UserNickNameEntity {

    @Id // primary key(기본키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 index 증가
    private Long nicknameId;

    @Column(name = "nickname", unique = true) // email은 unique(단일 조건)
    private String nickname;

    @OneToOne // 1:1 양방향을 위해 UserEntity의 userEntity를 참조 (외래키)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Builder // 생성자 대신 빌더 사용 (변경 가능성을 최소화) -> 생성자 패턴 중 빌더 패턴
    public UserNickNameEntity(String nickname, UserEntity userEntity) {
        this.nickname = nickname;
        this.userEntity = userEntity;
    }
}
