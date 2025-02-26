package com.asha.springboot.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asha.springboot.domain.user.entity.UserEntity;

/**
 * UserEntity를 위한 레포지토리
 * <p>
 * 데이터베이스에 접근하기 위해 ORM인 jpa를 사용
 * </p>
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * username이 존재하는지 확인
     * 
     * @param username
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * username으로 UserEntity 찾기
     * 
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);

    UserEntity findByUserId(Long userId);
}
