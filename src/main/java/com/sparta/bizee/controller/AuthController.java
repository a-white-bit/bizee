package com.sparta.bizee.controller;

import com.sparta.bizee.dto.AuthRequestDto;
import com.sparta.bizee.dto.AuthResponseDto;
import com.sparta.bizee.dto.RegisterRequestDto;
import com.sparta.bizee.dto.RegisterResponseDto;
import com.sparta.bizee.entity.User;
import com.sparta.bizee.service.UserService;
import com.sparta.bizee.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // JWT 발급이 정상적으로 이루어지는지 테스트해보는 API
    // 실제 서비스에 사용되지 않음
    // 로그인 기능인 login API가 대체함
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

    // JWT 검증이 정상적으로 이루어지는지 테스트해보는 API
    // 실제 서비스에 사용되지 않음
    //------공부용 메모------
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

    // 회원가입 API
    // 사용자 정보를 DB에 저장
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto req) {
        try {
            userService.register(req);
            RegisterResponseDto registerResponse = new RegisterResponseDto("유저 등록 성공");
            return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisterResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // 유저 로그인 API
    // 사용자 정보를 DB와 대조하여 일치하면 JWT 발급
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequest, HttpServletResponse res) {
        try {
            User user = userService.login(authRequest.getUsername(), authRequest.getPassword());
            String token = JwtUtil.createToken(user.getUsername());

            // 헤더에 저장
            JwtUtil.addJwtToCookie(token, res);

            AuthResponseDto authResponse = new AuthResponseDto("로그인 성공", token);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponseDto("유효하지 않은 사용자 이름 혹은 비밀번호", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
