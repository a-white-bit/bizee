package com.sparta.bizee.dto.response;

import com.sparta.bizee.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/*
 * -----------(학습용 메모)----------
 * [JSON <-- 객체]로 변환  : 직렬화
 * Jackson 라이브러리가 해줌
 * 프로퍼티 값을 읽을 수 있는 메서드 @Getter 필요
 * 결론: 직렬화는 @Getter 필요
 * --------------------------------
 */
@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String responsibility;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.responsibility = schedule.getResponsibility();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
