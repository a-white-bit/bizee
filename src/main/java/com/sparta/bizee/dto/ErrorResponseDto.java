package com.sparta.bizee.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 추가
@RequiredArgsConstructor
public class ErrorResponseDto {
    private final String code;
    private final String message;
}