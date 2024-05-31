package com.sparta.bizee.controller;

import com.sparta.bizee.dto.CommentRequestDto;
import com.sparta.bizee.dto.CommentResponseDto;
import com.sparta.bizee.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule/comment")
public class CommentController {
    CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 등록
    @PostMapping("/post")
    public CommentResponseDto createComment(@Valid @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }
}
