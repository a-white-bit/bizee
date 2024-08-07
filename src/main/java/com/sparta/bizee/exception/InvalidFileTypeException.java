package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class InvalidFileTypeException extends CustomException {
    public InvalidFileTypeException(ResponseCodeEnum code) {
        super(code);
    }
}