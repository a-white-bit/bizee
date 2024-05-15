package com.sparta.bizee.dto;

import com.sparta.bizee.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private int id;
    private String title;
    private String content;
    private String responsibility;
    private String creationDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.responsibility = schedule.getResponsibility();
        this.creationDate = schedule.getCreationDate();
    }
}
