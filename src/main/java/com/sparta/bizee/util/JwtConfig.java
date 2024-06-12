package com.sparta.bizee.util;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.util.Base64;

@Configuration
public class JwtConfig {
    // 환경변수 넣어주기 위한 config class
    @Value("${JWT_SECRET_KEY}") // BASE64 encoded
    private String SECRET_KEY;

    public static Key key;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        key = Keys.hmacShaKeyFor(bytes);
    }
}
