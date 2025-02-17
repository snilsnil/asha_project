package com.asha.springboot.global.security.userDetail;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.asha.springboot.domain.user.dto.UserDTO;

import lombok.Builder;

/**
 * 사용자 정보를 UserDetails로 변환
 * 
 */
public class CustomUserDetails implements UserDetails {
    private final UserDTO userDTO;

    @Builder
    public CustomUserDetails(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한을 부여하는 메소드

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {

        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {

        return userDTO.getUsername();
    }

    public String getRole() {

        return userDTO.getRole();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지 확인

        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠기지 않았는지 확인

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 자격이 만료되지 않았는지 확인

        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화 되었는지 확인

        return true;
    }

}
