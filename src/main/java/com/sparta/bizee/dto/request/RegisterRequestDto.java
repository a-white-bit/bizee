package com.sparta.bizee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequestDto {
    private String nickname;

    @NotBlank(message = "사용자 이름은 필수입니다.")
    @Size(min = 4, max = 10, message = "사용자 이름은 4자리 이상 10자리 이하만 가능합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$", message = "알파벳 소문자와 숫자 조합만 가능합니다.")
    private String username;    // 알파벳 소문자, 숫자로 구성

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자리 이상 15자리 이하만 가능합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "알파벳과 숫자 조합만 가능합니다.")
    private String password;    // 알파벳 대소문자, 숫자로 구성

    private boolean admin = false;

    private String adminToken;
}
