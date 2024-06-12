package com.sparta.bizee.filter;

import com.sparta.bizee.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Jwt Filter 로직을 구현하는 클래스
// 이 필터 클래스가 동작하도록 설정하는 클래스가 필요 --> JwtFilterConfig.java
@Slf4j(topic = "JwtFilter")
@Component
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        // ServletRequest -> HttpServletRequest 다운캐스팅
        // ServletRequest: 프로토콜 상관없이 요청 정보를 다루는 인터페이스
        // HttpServletRequest: ServletRequest의 하위 인터페이스. ServletRequest 기능 + HTTP 관련 기능
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authorizationHeader = httpServletRequest.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        String token = JwtUtil.substringToken(authorizationHeader);

        if (token == null || token.contains(" ")) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지 않거나 유효하지 않은 Authorization 헤더");
            log.error("잘못된 토큰값 요청: {}", authorizationHeader);
            return;
        }

        try {
            // JWT 검증
            if (JwtUtil.validateToken(token)) {
                httpServletRequest.setAttribute("username", JwtUtil.getUsernameFromToken(token));

                // 필터가 있으면 다음 필터가 계속해서 호출되고, 없으면 서블릿이 호출됨
                filterChain.doFilter(servletRequest, servletResponse);
                // 서블릿 서비스 로직 실행 후 이곳을 지남 (후처리)
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 유효하지 않습니다.");
                log.error("유효하지 않은 토큰: {}", token);
            }
        } catch (JwtException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 유효하지 않습니다.");
            log.error("JwtException: {}", token);
        }
    }
}
