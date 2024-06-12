package com.sparta.bizee.service;

import com.sparta.bizee.Repository.ScheduleRepository;
import com.sparta.bizee.dto.ScheduleDeleteRequestDto;
import com.sparta.bizee.dto.ScheduleRequestDto;
import com.sparta.bizee.dto.ScheduleResponseDto;
import com.sparta.bizee.entity.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    public ScheduleResponseDto getSchedule(long id) {
        // ID로 일정 찾기
        Schedule result = findSchedule(id);

        // ResponseDto로 전달
        return new ScheduleResponseDto(result);
    }

    public List<ScheduleResponseDto> getSchedules() {
        // 전체 일정 조회
        List<Schedule> result = scheduleRepository.findAllByOrderByCreatedAtDesc();

        // 비어있는 일정 리스트인지 확인
        if (result.isEmpty()) {
            throw new IllegalArgumentException("아무 일정도 없습니다.");
        } else {
            // List<Schedule> -> List<ScheduleResponseDto> 변환
            return result.stream().map(ScheduleResponseDto::new).toList();
        }
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(long id, ScheduleRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 암호 확인
        isCorrectKey(schedule.getPassKey(), requestDto.getPassKey());

        // 직접 엔티티 setter로 수정
        schedule.update(requestDto);

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }

    public long deleteSchedule(long id, ScheduleDeleteRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 암호 확인
        isCorrectKey(schedule.getPassKey(), requestDto.getPassKey());

        // 제거
        scheduleRepository.delete(schedule);

        // 제거된 ID 전달
        return schedule.getId();
    }

    private Schedule findSchedule(long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 일정이 삭제되었거나 찾을 수 없습니다."));
    }

    private void isCorrectKey(String key, String requestKey) {
        // 암호 확인
        if (!Objects.equals(key, requestKey)) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }
    }
}