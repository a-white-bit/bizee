package com.sparta.bizee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.bizee.dto.request.LoginRequestDto;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// WebSecurityConfig 에 의해 Bean 등록됨
// JWT 인증 필터
@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /*
     * UsernamePasswordAuthenticationFilter :
     * 기본적으로 '/login' POST 요청을 처리하며 별도의 Spring MVC Controller 를 지정하지 않아도 응답 처리 해줌
     */

    public JwtAuthenticationFilter() {
        // /login 엔드포인트로 들어오는 POST 요청은 이 필터 JwtAuthenticationFilter 에 의해 처리됨
        // 바꿔 말하면 다른 API 요청은 이 필터를 무시함
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        String role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getAuthority();

        String token = JwtUtil.createToken(username, role);
        JwtUtil.addJwtToCookie(token, response);

        ResponseCodeEnum status = ResponseCodeEnum.SUCCESS_LOGIN;
        // Response 의 Body 정보를 JSON 형태로 반환
        // 서블릿 전이므로 @ResponseBody Jackson 자동 변환 기술 사용 불가
        // JSON 으로 세팅, 매핑, 반영
        response.setStatus(status.getStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String result = new ObjectMapper().writeValueAsString(new ResponseDto(status));
        response.getWriter().write(result);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        ResponseCodeEnum status;
        if (failed instanceof BadCredentialsException) {
            status = ResponseCodeEnum.WRONG_PASSWORD;
        } else if (failed instanceof DisabledException) {
            status = ResponseCodeEnum.DISABLED_USER;
        } else if (failed instanceof LockedException) {
            status = ResponseCodeEnum.LOCKED_USER;
        } else {
            status = ResponseCodeEnum.FAIL_LOGIN;
        }
        // Response 의 Body 정보를 JSON 형태로 반환
        // 서블릿 전이므로 @ResponseBody Jackson 자동 변환 기술 사용 불가
        // JSON 으로 세팅, 매핑, 반영
        response.setStatus(status.getStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String result = new ObjectMapper().writeValueAsString(new ResponseDto(status));
        response.getWriter().write(result);
    }
}