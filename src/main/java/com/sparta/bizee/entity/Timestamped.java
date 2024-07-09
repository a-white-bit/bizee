package com.sparta.bizee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Auditing 설명
 * 타임스탬프 엔티티는 멤버변수에 @CreatedDate 또는 @CreationTimestamp 사용 가능
 * @CreatedDate: JPA 어노테이션
 * @CreationTimestamp: Hibernate 어노테이션
 *
 * @CreatedDate 사용 시,
 * 해당 클래스에 @EntityListeners(AuditingEntityListener.class) 추가하고
 * 메인 클래스에 @EnableJpaAuditing 추가 필요함
 */
// @EntityListeners(AuditingEntityListener.class) // + Main 클래스 @EnableJpaAuditing
@MappedSuperclass
@Getter
public abstract class Timestamped {
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
