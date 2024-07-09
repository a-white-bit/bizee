package com.sparta.bizee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * AccessDeniedHandler
 * "인증된 사용자"이나, 인가되지 않은 접근일 때 호출됨
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseCodeEnum status = ResponseCodeEnum.ACCESS_DENIED;
        response.setStatus(status.getStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ResponseDto responseDto = new ResponseDto(status);
        String result = new ObjectMapper().writeValueAsString(responseDto);
        response.getWriter().write(result);
    }
}