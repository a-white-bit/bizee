package com.sparta.bizee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    @NotNull(message = "사용자 id를 입력해주세요.")
    Long userId;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}