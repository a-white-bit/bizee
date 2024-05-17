package com.sparta.bizee.service;

import com.sparta.bizee.Repository.ScheduleRepository;
import com.sparta.bizee.dto.ScheduleRequestDto;
import com.sparta.bizee.dto.ScheduleResponseDto;
import com.sparta.bizee.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto 객체를 Schedule로 매핑
        Schedule schedule = new Schedule(requestDto);
        Schedule result = scheduleRepository.save(schedule);

        // ResponseDto로 전달
        return new ScheduleResponseDto(result);
    }

    public ScheduleResponseDto getSchedule(int id) {
        // ID로 일정 찾기
        Schedule result = scheduleRepository.findById(id);

        // ResponseDto로 전달
        return new ScheduleResponseDto(result);
    }

    public List<ScheduleResponseDto> getSchedules() {
        // 전체 일정 조회
        List<Schedule> result = scheduleRepository.findAll();

        // List<Schedule> -> List<ScheduleResponseDto> 변환
        return result.stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto updateSchedule(ScheduleRequestDto requestDto) {
        // 변경 요청 데이터를 갖는 일정 인스턴스 생성
        Schedule request = new Schedule(requestDto);
        Schedule changedSchedule = scheduleRepository.update(request);

        // ResponseDto로 전달
        return new ScheduleResponseDto(changedSchedule);
    }

    public int deleteSchedule(ScheduleRequestDto requestDto) {
        // 변경 요청 데이터를 갖는 일정 인스턴스 생성
        Schedule request = new Schedule(requestDto);
        int id = scheduleRepository.delete(request);

        // 제거된 ID 전달
        return id;
    }
}