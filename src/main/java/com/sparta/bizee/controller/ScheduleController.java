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

    // Schedule 인스턴스 저장소
    private final Map<Integer, Schedule> scheduleList = new HashMap<>();


    // @RequestParam: url path에 추가된 쿼리스트링 받기
    // @RequestBody: html body로 JSON 받아오기
    // @VariablePath: url path로 받기


    // 일정 등록
    // 반환: Dto
    //@ResponseBody : HTTP 응답데이터(body)에 자바 객체가 매핑되어 전달
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto 객체를 Entity로 mapping
        Schedule schedule = new Schedule(requestDto);

        // schedule Map의 Max ID 확인
        int maxId = !scheduleList.isEmpty() ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        // Map에 저장
        scheduleList.put(maxId, schedule);

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }


    // 일정 조회
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable int id) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        if (scheduleList.containsKey(id)) {
            // 해당 메모 가져오기
            Schedule schedule = scheduleList.get(id);
            // ResponseDto로 전달
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }
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