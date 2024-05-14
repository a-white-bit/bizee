package com.sparta.bizee.dto;

import com.sparta.bizee.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private String title;
    private String content;
    private String responsibility;
    private String passKey;
    private String creationDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.responsibility = schedule.getResponsibility();
        this.passKey = schedule.getPassKey();
        this.creationDate = schedule.getCreationDate();
    }

//    public ScheduleResponseDto(Long id, String username, String contents) {
//        this.id = id;
//        this.username = username;
//        this.contents = contents;
//    }
}
