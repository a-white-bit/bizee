package com.sparta.bizee.entity;

import com.sparta.bizee.dto.ScheduleRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@EntityListeners(AuditingEntityListener.class) // + Main 클래스 @EnableJapAuditing
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column
    private String content;

    @Column
    private String responsibility;

    @Column(nullable = false)
    private String passKey;

    // 1:N Comment 에 의해 양방향 설정
    // 단방향이어도 로직에 문제가 없는지 고려후 삭제
    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments = new ArrayList<>();

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.responsibility = requestDto.getResponsibility();
        this.passKey = requestDto.getPassKey();
    }

    public void update(ScheduleRequestDto requestDto) {
        // 수정 작업
        // 요청에 그 내용이 오지 않았을 경우 기본 정보 유지
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
        if (requestDto.getResponsibility() != null) {
            this.responsibility = requestDto.getResponsibility();
        }
    }
}