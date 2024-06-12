package com.sparta.bizee.controller;

import com.sparta.bizee.dto.CommentContentResponseDto;
import com.sparta.bizee.dto.CommentRequestDto;
import com.sparta.bizee.dto.CommentResponseDto;
import com.sparta.bizee.dto.HttpStatusResponseDto;
import com.sparta.bizee.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 등록
    @PostMapping("/schedules/{scheduleId}/comments")
    public CommentResponseDto createComment(@NotNull(message = "일정 id를 입력해주세요.") @PathVariable Long scheduleId,
                                            @Valid @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(scheduleId, requestDto);
    }

    // 댓글 수정
    @PatchMapping("/schedules/{scheduleId}/comments/{commentId}")
    public CommentContentResponseDto updateComment(@NotNull(message = "일정 id를 입력해주세요.") @PathVariable Long scheduleId,
                                                   @NotNull(message = "댓글 id를 입력해주세요.") @PathVariable Long commentId,
                                                   @Valid @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(scheduleId, commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/schedules/{scheduleId}/comments/{commentId}")
    public HttpStatusResponseDto deleteComment(@NotNull(message = "일정 id를 입력해주세요.") @PathVariable Long scheduleId,
                                               @NotNull(message = "댓글 id를 입력해주세요.") @PathVariable Long commentId,
                                               @NotNull(message = "사용자 id를 입력해주세요.") @RequestParam Long userId) {
        return commentService.deleteComment(scheduleId, commentId, userId);
    }
}