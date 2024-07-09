package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    // CustomException 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto> handleCustomException(CustomException e) {
        log.warn("{} Exception: {}", e.getClass().getSimpleName(), e.getMessage());
        return new ResponseDto(e.getCode()).createResponseEntity();
    }

    // 그 외 예외처리들
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleAllException(Exception e) {
        log.warn("Unhandled Exception: {}", e.getMessage());
        return new ResponseDto(ResponseCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage()).createResponseEntity();
    }
}