package com.sparta.bizee.controller;

import com.sparta.bizee.dto.ScheduleRequestDto;
import com.sparta.bizee.dto.ScheduleResponseDto;
import com.sparta.bizee.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 각 메서드마다 @ResponseBody 한 것과 같음
@RequestMapping("/schedule")
public class ScheduleController {

    // DB 대신 저장소 사용
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // Request Param
    // @RequestBody: html body로 JSON 받아오기
    // @VariablePath:

    // 일정 등록
    // 반환: Dto
    //@ResponseBody : HTTP 응답데이터(body)에 자바 객체가 매핑되어 전달
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto 객체를 Entity로 mapping
        Schedule schedule = new Schedule(requestDto);

        // Entity(Memo) --> ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }


    // 일정 조회
    @GetMapping
    public void getSchedule() {

    }

    // 일정 수정
    @PutMapping
    public void updateSchedule() {

    }

    // 일정 삭제
    @DeleteMapping
    public void deleteSchedule() {

    }
}