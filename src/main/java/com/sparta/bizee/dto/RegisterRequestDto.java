package com.sparta.bizee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RegisterRequestDto {
    @NotBlank(message = "사용자 이름은 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$", message = "알파벳 소문자와 숫자 조합만 가능합니다.")
    private String username;    // 알파벳 소문자, 숫자로 구성

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "알파벳과 숫자 조합만 가능합니다.")
    private String password;    // 알파벳 대소문자, 숫자로 구성
    private String authority;
}
