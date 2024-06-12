package com.sparta.bizee.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtFilterConfig {
    // 필터 구현 클래스
    private final JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistrationBean() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);

        // 필터링 적용할 API URL 패턴
        registrationBean.addUrlPatterns("/schedules/*");

        return registrationBean;
    }
}