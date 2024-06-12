package com.sparta.bizee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AuthRequestDto {
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String username;
    @NotBlank(message = "암호를 입력해주세요.")
    private String password;
}
