package com.sparta.bizee.entity;

import com.sparta.bizee.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private int id;
    private String title;
    private String content;
    private String responsibility;
    private String passKey;
    private LocalDateTime creationDate;

    public Schedule(ScheduleRequestDto requestDto) {
        this.id = requestDto.getId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.responsibility = requestDto.getResponsibility();
        this.passKey = requestDto.getPassKey();
        this.creationDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}