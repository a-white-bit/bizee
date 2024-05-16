package com.sparta.bizee.exception;

import com.sparta.bizee.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    // 예외핸들링
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorResponseDto invalidRequestHandler(IllegalArgumentException e) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

}