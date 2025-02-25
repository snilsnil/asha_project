package com.asha.springboot.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.user.entity.UserNickNameEntity;

public interface UserNickNameRepository extends JpaRepository<UserNickNameEntity, Long> {
    boolean existsByNickname(String nickname);

    Optional<UserNickNameEntity> findByNickname(String string);

}
