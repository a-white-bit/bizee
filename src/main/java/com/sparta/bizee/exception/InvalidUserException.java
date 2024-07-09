package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class InvalidUserException extends CustomException {
    public InvalidUserException(ResponseCodeEnum code) {
        super(code);
    }
}