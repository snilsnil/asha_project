package com.asha.springboot.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asha.springboot.domain.user.dto.UserSignUpDTO;
import com.asha.springboot.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * 사용자에 관한 모든 API를 처리하는 컨트롤러
 */

@RestController // @Controller + @ResponseBody 로 restfulAPI를 위해 사용
@RequiredArgsConstructor // final로 선언된 필드를 생성자로 만들어줌
public class UserController {

    private final UserService userService;

    /**
     * 회원가입을 위한 API
     * 
     * @param userSignUpDTO
     * @return String
     */
    @PostMapping("/signup")
    public String signup(@RequestBody UserSignUpDTO userSignUpDTO) {
        System.out.println(userSignUpDTO);

        // 회원가입 정보를 받아서 DB에 저장하기
        String result = userService.createUser(userSignUpDTO);

        return result;
    }

}
