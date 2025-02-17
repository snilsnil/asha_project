package com.asha.springboot.domain.user.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asha.springboot.domain.user.dto.UserSignUpDTO;
import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.exception.UserAlreadyExistsException;
import com.asha.springboot.domain.user.repository.UserInfoRepository;
import com.asha.springboot.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;


/**
 * 사용자 정보를 처리하는 서비스
 * 
 */
@RequiredArgsConstructor // final로 선언된 필드를 생성자로 만들어줌
@Service // 서비스 빈으로 등록
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * 
     * <p>
     * 트랜잭션을 사용하여 회원가입 정보를 DB에 저장
     * </p>
     * 
     * <p>
     * 사용자 ID와 이메일 중복 확인 기능은 적었지만 미구현
     * </p>
     * 
     * @param userSignUpDTO
     * @return String
     */
    @Transactional // 트랜잭션 처리
    public String createUser(UserSignUpDTO userSignUpDTO) {
        try {
            boolean checkusername = userRepository.existsByUsername(userSignUpDTO.getUsername()); // username 중복 확인
            boolean checkEmail = userInfoRepository.existsByEmail(userSignUpDTO.getEmail()); // email 중복 확인

            System.out.println("checkusername: " + checkusername);

            if (checkusername && checkEmail) {
                throw new UserAlreadyExistsException("ID와 이메일이 존재합니다.");
            } else if (checkusername) {
                throw new UserAlreadyExistsException("ID가 존재합니다.");
            } else if (checkEmail) {
                throw new UserAlreadyExistsException("이메일이 존재합니다.");
            }

            // 비밀번호 암호화
            userSignUpDTO.passwordEncoder(passwordEncoder.encode(userSignUpDTO.getPassword()));

            // DTO를 Entity로 변환
            UserEntity userEntity = userSignUpDTO.toEntity();

            // 사용자 정보를 DB에 저장
            userEntity = userRepository.save(userEntity);
            userInfoRepository.save(userSignUpDTO.toInfoEntity(userEntity));

            return "success";

        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return e.getMessage();

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return "DB에서 오류가 생겼습니다. 잠시후 다시 시도하세요.";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "예상치 못한 오류가 생겼습니다. 잠시후 다시 시도하세요.";
        }
    }

    

}
