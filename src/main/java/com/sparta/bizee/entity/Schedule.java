package com.sparta.bizee.entity;

import com.sparta.bizee.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private int id;
    private String title;
    private String content;
    private String responsibility;
    private String passKey;
    private LocalDate creationDate;

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.responsibility = requestDto.getResponsibility();
        this.passKey = requestDto.getPassKey();
        this.creationDate = LocalDate.now();
    }
}