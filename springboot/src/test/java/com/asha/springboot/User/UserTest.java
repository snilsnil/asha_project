package com.asha.springboot.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.asha.springboot.domain.user.controller.UserController;
import com.asha.springboot.domain.user.dto.UserSignUpDTO;
import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.entity.UserRole;
import com.asha.springboot.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 사용자에 관한 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc // MockMvc를 사용한 http 요청 테스트
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private UserSignUpDTO userSignUpDTO1;
    private UserSignUpDTO userSignUpDTO2;

    @BeforeEach
    public void setup() {
        userSignUpDTO1 = new UserSignUpDTO("admin", "admin", "test@test.com", "admin", "ADMIN");
        userSignUpDTO2 = new UserSignUpDTO("admin2", "admin2", "test2@test.com", "admin2", "ADMIN");
    }

    @Test
    public void testEnum() {
        System.out.println(UserRole.valueOf("ADMIN"));
    }

    /**
     * 사용자 생성 후, DB 저장 테스트
     * 
     * @throws Exception
     */
    @Test
    void creatUser() throws Exception {

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        asJsonString(userSignUpDTO1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        asJsonString(userSignUpDTO2)))
                .andExpect(status().isOk());
    }

    /**
     * 사용자 조회 테스트
     */
    @Test
    void findUser() {
        UserEntity userEntity = userRepository.findByUsername("admin");
        System.out.println(userEntity);
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
