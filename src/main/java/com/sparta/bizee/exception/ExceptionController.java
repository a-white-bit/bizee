package com.sparta.bizee.exception;

import com.sparta.bizee.dto.ErrorResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ErrorResponseDto scheduleExceptionHandler(IllegalArgumentException e) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    // 잘못된 Body Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponseDto invalidBodyRequestHandler(MethodArgumentNotValidException e) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    // 잘못된 Param Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorResponseDto invalidParamRequestHandler(ConstraintViolationException e) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

}