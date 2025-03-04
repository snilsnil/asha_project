package com.asha.springboot.domain.user.dto;

import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.entity.UserInfoEntity;
import com.asha.springboot.domain.user.entity.UserNickNameEntity;
import com.asha.springboot.domain.user.entity.UserRole;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignUpDTO {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String role;

    private UserRole admin = UserRole.ADMIN;
    private UserRole user = UserRole.USER;

    @Builder
    public UserSignUpDTO(String username, String password, String email, String nickname, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    /**
     * DB와 사용하기 위해 UserEntity로 변환
     * 
     * @return UserEntity
     */
    public UserEntity toEntity(String passwordEncoder, String userRole) {
        return UserEntity.builder()
                .username(username)
                .password(passwordEncoder)
                .role(UserRole.valueOf(userRole))
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
