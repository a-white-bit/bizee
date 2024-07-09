package com.sparta.bizee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// WebSecurityConfig 에 의해 Bean 등록됨
// JWT 인가 필터 사용자 정의
@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        String tokenValue = JwtUtil.getTokenFromRequest(req);

        if (StringUtils.hasText(tokenValue)) {
            // JWT 토큰 substring
            tokenValue = JwtUtil.substringToken(tokenValue);
            log.info(tokenValue);

            if (!JwtUtil.validateToken(tokenValue)) {
                log.error("Token Error");
                unauthorized(res, ResponseCodeEnum.TOKEN_ERROR);
                return;
            }
            try {
                setAuthentication(JwtUtil.getUsernameFromToken(tokenValue));
            } catch (Exception e) {
                log.error(e.getMessage());
                unauthorized(res, ResponseCodeEnum.INTERNAL_SERVER_ERROR);
                return;
            }
        }

        filterChain.doFilter(req, res);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    // 인가되지 않음
    private void unauthorized(HttpServletResponse res, ResponseCodeEnum status) throws IOException {
        // Response 의 Body 정보를 JSON 형태로 반환
        // 서블릿 전이므로 @ResponseBody Jackson 자동 변환 기술 사용 불가
        // JSON 으로 세팅, 매핑, 반영
        res.setStatus(status.getStatusCode());
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        String result = new ObjectMapper().writeValueAsString(new ResponseDto(status));
        res.getWriter().write(result);
    }
}