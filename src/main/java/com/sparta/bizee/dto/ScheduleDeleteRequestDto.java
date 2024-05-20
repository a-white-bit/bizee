package com.sparta.bizee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDeleteRequestDto {
        @NotBlank(message = "암호는 필수 입력사항입니다.")
        private String passKey;
}
