package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class UserRegistrationException extends CustomException {
    public UserRegistrationException(ResponseCodeEnum code) {
        super(code);
    }
}