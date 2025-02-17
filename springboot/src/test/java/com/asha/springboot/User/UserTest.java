package com.asha.springboot.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asha.springboot.domain.user.controller.UserController;
import com.asha.springboot.domain.user.dto.UserSignUpDTO;

/**
 * 사용자에 관한 테스트
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserController userController;

    /**
     * 사용자 생성 후, DB 저장 테스트
     */
    @Test
    void creatUser() {

        UserSignUpDTO userSignUpDTO = new UserSignUpDTO("amdin", "admin", "test@test.com", "admin");

        userController.signup(userSignUpDTO);

    }

}
