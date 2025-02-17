package com.asha.springboot.global.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asha.springboot.domain.user.dto.UserDTO;
import com.asha.springboot.domain.user.entity.UserEntity;
import com.asha.springboot.domain.user.repository.UserRepository;
import com.asha.springboot.global.security.userDetail.CustomUserDetails;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 정보를 DB에서 조회하여 UserDetails로 변환
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 사용자가 입력한 username을 받음

        // DB에서 조회
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity != null) {
            UserDTO userDTO = userEntity.toDTO();
            // UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userDTO);
        }

        return null;
    }
}
