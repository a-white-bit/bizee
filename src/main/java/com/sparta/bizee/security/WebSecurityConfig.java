package com.sparta.bizee.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Security Configuration 클래스
 * 인증/인가를 SESSION 방식이 아닌 JWT 방식으로 대체하였으므로
 * 재정의한 필터를 적용해야 함
 * 1. Filter Bean 등록
 * 2. Filter Chain 에 필터 등록. 필터 끼워 넣기
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 사용
public class WebSecurityConfig {

    // Bean 객체 authenticationConfiguration 으로부터 인증매니저를 get 가능 : getAuthenticationManager()
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    // Manager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // PasswordEncoder 필요
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // JWT 인증 필터 Bean 등록
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();

        // manager 객체 세팅
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    // JWT 인가 필터 Bean 등록
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(userDetailsService);
    }

    // 시큐리티 필터 체인 설정 Bean 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정: CSRF 보호 비활성
                .csrf((csrf) -> csrf.disable())
                //.csrf(AbstractHttpConfigurer::disable) 와 동일, 람다 <-> 메소드 참조의 차이, 스타일-가독성 고려.

                // 세션을 사용하지 않도록 정책 STATELESS 로 변경
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 인가 설정
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/register", "/login").permitAll()
                        .anyRequest().authenticated())

                // 기본 폼 로그인을 비활성화, 중복 인증 방지
                .formLogin((formLogin) -> formLogin.disable())

                // 인가 실패시 처리되는 핸들러
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler) // 접근 거부(인가 실패) 시 처리
                        .authenticationEntryPoint(customAuthenticationEntryPoint)) // 인증 실패 시 처리

                // 커스텀 필터 끼우기
                // JWT 인가필터 -> JWT 인증필터 -> UsernamePasswordAuthenticationFilter 순으로 설정
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);


        return http.build();
    }
}