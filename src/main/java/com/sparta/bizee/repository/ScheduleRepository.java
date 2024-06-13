package com.sparta.bizee.repository;

import com.sparta.bizee.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 쿼리 메소드
    // 작성일자 내림차순
    List<Schedule> findAllByOrderByCreatedAtDesc();
}