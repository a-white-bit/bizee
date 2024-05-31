package com.sparta.bizee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotNull(message = "사용자 고유번호를 입력해주세요.")
    private long userId;
    @NotNull(message = "일정 고유번호를 입력해주세요.")
    private long scheduleId;
}