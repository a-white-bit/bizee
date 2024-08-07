package com.sparta.bizee.entity;

import com.sparta.bizee.dto.request.ScheduleRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "schedule")
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
    // 외래키로 인해서 일정 삭제시 exception이 발생하고 삭제되지 않음 --> 해결법: 고아 엔티티 삭제 옵션 true 하기
    // 항상 댓글 정보를 불러오지 않으므로 지연 로딩 설정 LAZY
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Schedule(String title, String content, String responsibility, String passKey) {
        this.title = title;
        this.content = content;
        this.responsibility = responsibility;
        this.passKey = passKey;
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