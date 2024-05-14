package com.sparta.bizee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String content;
    private String responsibility;
    private String passKey;

    public ScheduleRequestDto(String title) {
        this.title = title;
    }

    public ScheduleRequestDto(String title, String content, String responsibility, String passKey) {

    }

    public ScheduleRequestDto(String title, String passKey) {

    }
}
