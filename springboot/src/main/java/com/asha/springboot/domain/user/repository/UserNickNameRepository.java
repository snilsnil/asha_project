package com.asha.springboot.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asha.springboot.domain.user.entity.UserNickNameEntity;

public interface UserNickNameRepository extends JpaRepository<UserNickNameEntity, Long> {
    boolean existsByNickname(String nickname);

}
