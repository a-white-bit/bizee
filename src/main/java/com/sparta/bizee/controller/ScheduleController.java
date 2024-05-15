package com.sparta.bizee.controller;

import com.sparta.bizee.dto.ScheduleRequestDto;
import com.sparta.bizee.dto.ScheduleResponseDto;
import com.sparta.bizee.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController // 각 메서드마다 @ResponseBody 한 것과 같음
@RequestMapping("/schedule")
public class ScheduleController {

    // Schedule 인스턴스 저장소
    private final Map<Integer, Schedule> scheduleList = new HashMap<>();

    /* -------------(학습용 메모)---------------
    * Controller Parameter Annotation 구분하기!!
    * @RequestParam: url path에 추가된 쿼리스트링 받기
    * @RequestBody: html body로 JSON 받아오기
    * @VariablePath: url path로 받기
    -----------------------------------------*/

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

        //
        // 예외처리: passkey가 null로 등록하려고 하면 --> 취소
        //

        // Map에 저장
        scheduleList.put(maxId, schedule);

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }


    // 일정 조회
    // 반환: Dto
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable int id) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        if (scheduleList.containsKey(id)) {
            // 해당 일정 가져오기
            Schedule schedule = scheduleList.get(id);
            // ResponseDto로 전달
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
            // 예외 throw 대신 클라이언트에게 책임 묻기 (서버는 문제 없는디?)
        }
    }

    // 일정 전체 조회
    // 반환: Dto 리스트
    @GetMapping
    public List<ScheduleResponseDto> getSchedules() {
        // Map To List
        return scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();
    }

    // 일정 수정
    // 반환: Dto
    @PutMapping
    public ScheduleResponseDto updateSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        int id = requestDto.getId();
        if (!scheduleList.containsKey(id)) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
            // 예외 throw 대신 클라이언트에게 책임 묻기 (서버는 문제 없는디?)
        }

        // 해당 일정 가져오기
        Schedule schedule = scheduleList.get(id);
        // 암호가 일치하지 않는지 확인
        if (!Objects.equals(schedule.getPassKey(), requestDto.getPassKey())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
            // 예외 throw 대신 클라이언트에게 책임 묻기 (서버는 문제 없는디?)
        }

        // 수정
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setResponsibility(requestDto.getResponsibility());

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제
    // 반환: id
    @DeleteMapping
    public int deleteSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        int id = requestDto.getId();
        if (!scheduleList.containsKey(id)) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
            // 예외 throw 대신 클라이언트에게 책임 묻기 (서버는 문제 없는디?)
        }

        // 해당 일정 가져오기
        Schedule schedule = scheduleList.get(id);
        // 암호가 일치하지 않는지 확인
        if (!Objects.equals(schedule.getPassKey(), requestDto.getPassKey())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
            // 예외 throw 대신 클라이언트에게 책임 묻기 (서버는 문제 없는디?)
        }

        // 삭제
        scheduleList.remove(id);
        return id;
    }
}