package com.sparta.bizee.dto.response;

import com.sparta.bizee.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long userId;
    private final Long scheduleId;
    private final Long commentId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentResponseDto(Comment comment, Long scheduleId) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.createdAt = comment.getCreatedAt();
        this.scheduleId = scheduleId;
        this.updatedAt = comment.getUpdatedAt();
    }
}
