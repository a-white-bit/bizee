package com.sparta.bizee.controller;

import com.sparta.bizee.dto.request.CommentRequestDto;
import com.sparta.bizee.dto.response.CommentResponseDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.dto.response.ResponseDto;
import com.sparta.bizee.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}")
public class CommentController {
    private final CommentService commentService;
    private ResponseCodeEnum status;

    // 댓글 등록
    @PostMapping("/comments")
    public ResponseEntity<ResponseDto> createComment(
            @NotNull(message = "일정 id를 입력해주세요.") @PathVariable Long scheduleId,
            @Valid @RequestBody CommentRequestDto requestDto) {
        status = ResponseCodeEnum.SUCCESS;
        CommentResponseDto commentResponseDto = commentService.createComment(scheduleId, requestDto);
        return new ResponseDto(status, commentResponseDto).createResponseEntity();
    }

    // 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(
            @NotNull(message = "일정 id를 입력해주세요.") @PathVariable Long scheduleId,
            @NotNull(message = "댓글 id를 입력해주세요.") @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto) {
        status = ResponseCodeEnum.SUCCESS;
        CommentResponseDto commentResponseDto = commentService.updateComment(scheduleId, commentId, requestDto);
        return new ResponseDto(status, commentResponseDto).createResponseEntity();
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(
            @NotNull(message = "일정 id를 입력해주세요.") @PathVariable Long scheduleId,
            @NotNull(message = "댓글 id를 입력해주세요.") @PathVariable Long commentId,
            @NotNull(message = "사용자 id를 입력해주세요.") @RequestParam Long userId) {
        status = ResponseCodeEnum.SUCCESS;
        commentService.deleteComment(scheduleId, commentId, userId);
        return new ResponseDto(status).createResponseEntity();
    }
}