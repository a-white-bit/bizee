package com.sparta.bizee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * AuthenticationEntryPoint
 * "인증되지 않은 사용자"가 접근 시 호출
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseCodeEnum status = ResponseCodeEnum.UNAUTHORIZED;
        response.setStatus(status.getStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ResponseDto responseDto = new ResponseDto(status);
        String result = new ObjectMapper().writeValueAsString(responseDto);
        response.getWriter().write(result);
    }
}
