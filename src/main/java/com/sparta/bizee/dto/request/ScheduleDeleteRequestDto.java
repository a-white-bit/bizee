package com.sparta.bizee.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleDeleteRequestDto {
    @NotBlank(message = "암호는 필수 입력사항입니다.")
    private String passKey;
}
