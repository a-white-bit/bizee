package com.sparta.bizee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private int id;
    private String title;
    private String content;
    private String responsibility;
    private String passKey;
    private String creationDate;

    // 등록 API 요청 리소스 전달
    public ScheduleRequestDto(String title, String content, String responsibility, String passKey, String creationDate) {
        this.title = title;
        this.content = content;
        this.responsibility = responsibility;
        this.passKey = passKey;
        this.creationDate = creationDate;
    }

    // 수정 API 요청 리소스 전달
    public ScheduleRequestDto(int id, String title, String content, String responsibility, String passKey) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.responsibility = responsibility;
        this.passKey = passKey;
    }

    // 삭제 API 요청 리소스 전달
    public ScheduleRequestDto(int id, String passKey) {
        this.id = id;
        this.passKey = passKey;
    }
}
