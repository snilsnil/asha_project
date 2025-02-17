package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.entity.UserInfoEntity;
import com.asha.springboot.domain.user.entity.UserNickNameEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입에 관한 DTO
 * 
 * @param username
 * @param password
 * @param email
 * 
 * 
 */
@Getter
public class UserSignUpDTO {
    private String username;
    private String password;
    private String email;
    private String nickname;

    /**
     * 회원가입 DTO 생성자 (username, password, email)
     * 
     * @param username
     * @param password
     * @param email
     * @param nickname
     */
    @Builder // 생성자 대신 빌더 사용 (변경 가능성을 최소화) -> 생성자 패턴 중 빌더 패턴
    public UserSignUpDTO(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    /**
     * 암호화된 비밀번호로 변환
     * 
     * @param password
     */
    public void passwordEncoder(String password) {
        this.password = password;
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
                .role("customer")
                .build(); // entity 객체 생성
    }

    /**
     * DB와 사용하기 위해 UserInfoEntity로 변환
     * 
     * @param userEntity
     * @return UserInfoEntity
     */
    public UserInfoEntity toInfoEntity(UserEntity userEntity) {
        return UserInfoEntity.builder()
                .email(email)
                .userEntity(userEntity)
                .build(); // entity 객체 생성
    }

    /**
     * DB와 사용하기 위해 UserInfoEntity로 변환
     * 
     * @param userEntity
     * @return UserInfoEntity
     */
    public UserNickNameEntity toNickNameEntity(UserEntity userEntity) {
        return UserNickNameEntity.builder()
                .nickname(nickname)
                .userEntity(userEntity)
                .build(); // entity 객체 생성
    }

}
