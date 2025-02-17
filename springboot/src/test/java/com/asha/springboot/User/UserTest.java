package com.asha.springboot.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asha.springboot.domain.user.controller.UserController;
import com.asha.springboot.domain.user.dto.UserSignUpDTO;
import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.repository.UserRepository;

/**
 * 사용자에 관한 테스트
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    /**
     * 사용자 생성 후, DB 저장 테스트
     */
    @Test
    void creatUser() {

        UserSignUpDTO userSignUpDTO = new UserSignUpDTO("admin", "admin", "test@test.com", "admin");

        userController.signup(userSignUpDTO);

    }

    /**
     * 사용자 조회 테스트
     */
    @Test
    void findUser() {
        UserEntity userEntity = userRepository.findByUsername("admin");
        System.out.println(userEntity);
    }

}
