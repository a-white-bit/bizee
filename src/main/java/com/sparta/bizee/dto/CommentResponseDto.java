package com.sparta.bizee.dto;

import com.sparta.bizee.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final Long userId;
    private final LocalDateTime commentDate;
    private final Long scheduleId;

    public CommentResponseDto(Comment comment, Long scheduleId) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.commentDate = comment.getCommentDate();
        this.scheduleId = scheduleId;
    }
}
