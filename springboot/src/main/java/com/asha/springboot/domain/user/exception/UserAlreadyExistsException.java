package com.asha.springboot.domain.user.exception;

/**
 * 이미 존재하는 사용자가 있을 때 발생하는 예외
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
