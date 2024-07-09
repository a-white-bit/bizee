package com.sparta.bizee.service;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.exception.ScheduleServiceException;
import com.sparta.bizee.repository.ScheduleRepository;
import com.sparta.bizee.dto.request.ScheduleDeleteRequestDto;
import com.sparta.bizee.dto.request.ScheduleRequestDto;
import com.sparta.bizee.dto.response.ScheduleResponseDto;
import com.sparta.bizee.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .responsibility(requestDto.getResponsibility())
                .passKey(requestDto.getPassKey())
                .build();
        scheduleRepository.save(schedule);

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto getSchedule(Long id) {
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
            throw new ScheduleServiceException(ResponseCodeEnum.NOTHING_SCHEDULE);
        } else {
            // List<Schedule> -> List<ScheduleResponseDto> 변환
            return result.stream().map(ScheduleResponseDto::new).toList();
        }
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 암호 확인
        isCorrectKey(schedule.getPassKey(), requestDto.getPassKey());

        // 수정
        schedule.update(requestDto);

        // updatedAt 갱신
        scheduleRepository.save(schedule);

        // ResponseDto로 전달
        return new ScheduleResponseDto(schedule);
    }

    public void deleteSchedule(Long id, ScheduleDeleteRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        // 암호 확인
        isCorrectKey(schedule.getPassKey(), requestDto.getPassKey());

        // 제거
        scheduleRepository.delete(schedule);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new ScheduleServiceException(ResponseCodeEnum.SCHEDULE_NOT_FOUND));
    }

    private void isCorrectKey(String key, String requestKey) {
        if (!Objects.equals(key, requestKey)) {
            throw new ScheduleServiceException(ResponseCodeEnum.WRONG_PASSWORD);
        }
    }
}