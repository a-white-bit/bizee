package com.sparta.bizee.dto;

import com.sparta.bizee.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final long id;
    private final String title;
    private final String content;
    private final String responsibility;
    private final LocalDateTime creationDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.responsibility = schedule.getResponsibility();
        this.creationDate = schedule.getCreationDate();
    }
}
