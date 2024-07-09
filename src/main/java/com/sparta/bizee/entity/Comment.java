package com.sparta.bizee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 공부용 메모--------------------
 * [빌더 패턴]
 *
 * 클래스 수준의 @Builder: "모든 필드 생성자로" 빌더 클래스 자동 생성
 * @AllArgsConstructor 필요
 *
 * 메서드 수준의 @Builder: 특정 생성자 또는 메서드의 빌더 패턴 가능
 * 특정 필드만 초기화할 때 사용
 *
 * [엔티티]
 * JPA 에서 데이터베이스에서부터 객체와 상호작용하기 위해서는
 * @Getter, @NoArgsConstructor 반드시 필요
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // N:1 외래키의 주인 Comment
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    // Builder 패턴이 사용할 생성자
    // 개발자가 직접 사용하지는 않을 것 (private) --> .builder().content().build() 로 사용 (public)
    @Builder
    private Comment(String content, Long userId, Schedule schedule) {
        this.content = content;
        this.userId = userId;
        this.schedule = schedule;
    }

    public void update(String content) {
        this.content = content;
    }
}