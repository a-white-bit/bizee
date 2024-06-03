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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 등록
    @PostMapping("/comment")
    public CommentResponseDto createComment(@NotNull(message = "일정 id를 입력해주세요.") @RequestParam Long scheduleId,
                                            @NotNull(message = "사용자 id를 입력해주세요.") @RequestParam Long userId,
                                            @Valid @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(scheduleId, userId, requestDto);
    }

    // 댓글 수정
    @PatchMapping("/comment")
    public CommentContentResponseDto updateComment(@NotNull(message = "댓글 id를 입력해주세요.") @RequestParam Long commentId,
                                                   @NotNull(message = "사용자 id를 입력해주세요.") @RequestParam Long userId,
                                                   @Valid @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(commentId, userId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment")
    public HttpStatusResponseDto deleteComment(@NotNull(message = "댓글 id를 입력해주세요.") @RequestParam Long commentId,
                                               @NotNull(message = "사용자 id를 입력해주세요.") @RequestParam Long userId) {
        return commentService.deleteComment(commentId, userId);
    }
}
