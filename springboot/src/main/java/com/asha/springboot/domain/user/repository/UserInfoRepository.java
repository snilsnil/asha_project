package com.asha.springboot.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.user.entity.UserInfoEntity;

/**
 * UserInfoEntity를 위한 레포지토리
 * <p>
 * 데이터베이스에 접근하기 위해 ORM인 jpa를 사용
 * </p>
 */

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

    /**
     * email이 존재하는지 확인
     * 
     * @param byEmail
     * @return boolean
     */
    boolean existsByEmail(String byEmail);

}
