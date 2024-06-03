package com.sparta.bizee.controller;

import com.sparta.bizee.dto.HttpStatusResponseDto;
import com.sparta.bizee.exception.InvalidFileTypeException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionController {

    // 잘못된 파라미터
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public HttpStatusResponseDto scheduleExceptionHandler(IllegalArgumentException e) {
        return new HttpStatusResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    // 잘못된 Body Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public HttpStatusResponseDto invalidBodyRequestHandler(MethodArgumentNotValidException e) {
        return new HttpStatusResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    // 잘못된 Param Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public HttpStatusResponseDto invalidParamRequestHandler(ConstraintViolationException e) {
        return new HttpStatusResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    // 잘못된 File type Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFileTypeException.class)
    @ResponseBody
    public HttpStatusResponseDto invalidFileTypeRequestHandler(InvalidFileTypeException e) {
        return new HttpStatusResponseDto(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    // IOException
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public HttpStatusResponseDto failStreamFileRequestHandler(IOException e) {
        return new HttpStatusResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
    }

    // 혹시 모를 범용 Exception 캐치용
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public HttpStatusResponseDto commonRuntimeExceptionHandler(RuntimeException e) {
        return new HttpStatusResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
    }
}