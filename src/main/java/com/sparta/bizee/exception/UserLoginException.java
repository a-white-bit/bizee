package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class UserLoginException extends CustomException {
    public UserLoginException(ResponseCodeEnum code) {
        super(code);
    }
}