package com.sparta.bizee.Repository;

import com.sparta.bizee.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    // 쿼리 메소드
    // 작성일자 내림차순
    List<Schedule> findAllByOrderByCreationDateDesc();
}