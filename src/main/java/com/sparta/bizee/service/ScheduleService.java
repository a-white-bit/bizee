package com.sparta.bizee.service;

import com.sparta.bizee.dto.ScheduleRequestDto;
import com.sparta.bizee.dto.ScheduleResponseDto;
import com.sparta.bizee.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ScheduleService {
    // Schedule 인스턴스 저장소
    private final Map<Integer, Schedule> scheduleList = new HashMap<>();

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
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

    public ScheduleResponseDto getSchedule(int id) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        if (scheduleList.containsKey(id)) {
            // 해당 일정 가져오기
            Schedule schedule = scheduleList.get(id);
            // ResponseDto로 전달
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("해당 일정이 삭제되었거나 찾을 수 없습니다.");
        }
    }

    public List<ScheduleResponseDto> getSchedules() {
        // 비어있는 일정 리스트인지 확인
        if (scheduleList.isEmpty()) {
            throw new IllegalArgumentException("아무 일정도 없습니다.");
        }

        List<Schedule> mapToList = new ArrayList<>(scheduleList.values());

        // 날짜 내림차순 정렬
        Collections.sort(mapToList);

        // List<Schedule> -> List<ScheduleResponseDto> 변환
        return mapToList.stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto updateSchedule(ScheduleRequestDto requestDto) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        int id = requestDto.getId();
        if (!scheduleList.containsKey(id)) {
            throw new IllegalArgumentException("해당 일정이 삭제되었거나 찾을 수 없습니다.");
        }

        // 해당 일정 가져오기
        Schedule schedule = scheduleList.get(id);
        // 암호가 일치하지 않는지 확인
        if (!Objects.equals(schedule.getPassKey(), requestDto.getPassKey())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }

        // 수정
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setResponsibility(requestDto.getResponsibility());

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }


    public int deleteSchedule(ScheduleRequestDto requestDto) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        int id = requestDto.getId();
        if (!scheduleList.containsKey(id)) {
            throw new IllegalArgumentException("해당 일정이 삭제되었거나 찾을 수 없습니다.");
        }

        // 해당 일정 가져오기
        Schedule schedule = scheduleList.get(id);
        // 암호가 일치하지 않는지 확인
        if (!Objects.equals(schedule.getPassKey(), requestDto.getPassKey())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }

        // 삭제
        scheduleList.remove(id);
        return id;
    }
}


