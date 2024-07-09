package com.sparta.bizee.controller;

import com.sparta.bizee.dto.request.ScheduleDeleteRequestDto;
import com.sparta.bizee.dto.request.ScheduleRequestDto;
import com.sparta.bizee.dto.response.ScheduleResponseDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated // @PathVariable 유효성 검사 위함
@RestController // 각 메서드마다 @ResponseBody 한 것과 같음
@RequiredArgsConstructor
public class ScheduleController {

    // Memo Control <-> Service 분리
    private final ScheduleService scheduleService;
    private ResponseCodeEnum status;

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
    // @ResponseBody : HTTP 응답데이터(body)에 자바 객체가 매핑되어 전달
    // 클래스 애너테이션 @RestController 로 대체함
    @PostMapping("/schedules")
    public ResponseEntity<ResponseDto> createSchedule(
            @Valid @RequestBody ScheduleRequestDto requestDto) {
        status = ResponseCodeEnum.SUCCESS;
        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(requestDto);
        return new ResponseDto(status, scheduleResponseDto).createResponseEntity();
    }

    // 일정 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ResponseDto> getSchedule(
            @NotNull(message = "ID는 필수 입력사항입니다.") @PathVariable Long id) {
        status = ResponseCodeEnum.SUCCESS;
        ScheduleResponseDto scheduleResponseDto = scheduleService.getSchedule(id);
        return new ResponseDto(status, scheduleResponseDto).createResponseEntity();
    }

    // 일정 전체 조회
    @GetMapping("/schedules/all")
    public ResponseEntity<ResponseDto> getSchedules() {
        status = ResponseCodeEnum.SUCCESS;
        List<ScheduleResponseDto> scheduleResponseDto = scheduleService.getSchedules();
        return new ResponseDto(status, scheduleResponseDto).createResponseEntity();
    }

    // 일정 수정
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ResponseDto> updateSchedule(
            @NotNull(message = "ID는 필수 입력사항입니다.") @PathVariable Long id,
            @Valid @RequestBody ScheduleRequestDto requestDto) {
        status = ResponseCodeEnum.SUCCESS;
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto);
        return new ResponseDto(status, scheduleResponseDto).createResponseEntity();
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<ResponseDto> deleteSchedule(
            @NotNull(message = "ID는 필수 입력사항입니다.") @PathVariable Long id,
            @Valid @RequestBody ScheduleDeleteRequestDto requestDto) {

        status = ResponseCodeEnum.SUCCESS;
        scheduleService.deleteSchedule(id, requestDto);
        return new ResponseDto(status).createResponseEntity();
    }
}