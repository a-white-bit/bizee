package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class CommentServiceException extends CustomException {
    public CommentServiceException(ResponseCodeEnum code) {
        super(code);
    }
}