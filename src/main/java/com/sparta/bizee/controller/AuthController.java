package com.sparta.bizee.controller;

import com.sparta.bizee.dto.AuthRequestDto;
import com.sparta.bizee.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


// JWT Util 클래스가 제대로 동작하는지 확인하는 용도인 API
// 실제 서비스에 사용되지 않음, Filter 로 인증/인가 구현할 것임
@RestController
@RequiredArgsConstructor
public class AuthController {
    // JWT 발급 API
    @PostMapping("/auth")
    public String createToken(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse res) {
        // DB에서 사용자 확인
        String username = "user";
        String password = "password";

        if (username.equals(authRequestDto.getUsername()) && password.equals(authRequestDto.getPassword())) {
            // JWT 생성
            String token = JwtUtil.createToken(username);

            // 쿠키 저장
            JwtUtil.addJwtToCookie(token, res);
            return "토큰 생성 완료: " + token;
        } else {
            return "토큰 생성 실패: 사용자 정보를 찾을 수 없습니다.";
        }
    }

    // JWT 검증 API
    // @RequestHeader()는 아래와 동일
    // HttpServletRequest req
    // req.getHeader(HEADER_KEY)
    @GetMapping("/validate")
    public String validateToken(@RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String token) {
        String substrToken = JwtUtil.substringToken(token);
        if (substrToken == null) {
            return "토큰 검증 실패: 잘못된 토큰 형식 " + token;
        }
        if (JwtUtil.validateToken(substrToken)) {
            return "토큰 검증 성공: " + JwtUtil.getUsernameFromToken(substrToken) + "님";
        } else {
            return "토큰 검증 실패";
        }
    }
}
