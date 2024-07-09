package com.sparta.bizee.controller;

import com.sparta.bizee.dto.request.RegisterRequestDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입 API
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterRequestDto req) {
        authService.register(req);
        ResponseCodeEnum codeEnum = ResponseCodeEnum.SUCCESS_SIGN_UP;
        return new ResponseDto(codeEnum).createResponseEntity();
    }
}
