package com.sparta.bizee.dto;

import lombok.Getter;

@Getter
public class AuthResponseDto {
    private final String message;
    private final String token;

    public AuthResponseDto(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
