package com.sparta.bizee.controller;

import com.sparta.bizee.dto.ScheduleDeleteRequestDto;
import com.sparta.bizee.dto.ScheduleRequestDto;
import com.sparta.bizee.dto.ScheduleResponseDto;
import com.sparta.bizee.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated // @PathVariable 유효성 검사 위함
@RestController // 각 메서드마다 @ResponseBody 한 것과 같음
@RequestMapping("/schedule")
public class ScheduleController {

    // Memo Control <-> Service 분리
    ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /* -------------(학습용 메모)---------------
    * Controller Mapping Parameter annotation 구분하기!!
    * @RequestParam: url path에 추가된 쿼리스트링 받기
    * @RequestBody: html body로 JSON 받아오기
    * @VariablePath: url path로 받기
    *
    * (추가)
    * 만약 매개변수 추론불가 오류 발생시
    * @PathVariable("id") 혹은 @RequestParam("id") 처럼 이름을 직접 명시하기
    -----------------------------------------*/

    // 일정 등록
    // 반환: Dto
    //@ResponseBody : HTTP 응답데이터(body)에 자바 객체가 매핑되어 전달
    @PostMapping("/post")
    public ScheduleResponseDto createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 일정 조회
    // 반환: Dto
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@NotNull(message = "ID는 필수 입력사항입니다.") @PathVariable int id) {
        return scheduleService.getSchedule(id);
    }

    // 일정 전체 조회
    // 반환: Dto 리스트
    @GetMapping
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }

    // 일정 수정
    // 반환: Dto
    @PutMapping("/update/{id}")
    public ScheduleResponseDto updateSchedule(@NotNull(message = "ID는 필수 입력사항입니다.") @PathVariable int id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    // 일정 삭제
    // 반환: id
    @DeleteMapping("/delete/{id}")
    public int deleteSchedule(@NotNull(message = "ID는 필수 입력사항입니다.") @PathVariable int id, @Valid @RequestBody ScheduleDeleteRequestDto requestDto) {
        return scheduleService.deleteSchedule(id, requestDto);
    }
}