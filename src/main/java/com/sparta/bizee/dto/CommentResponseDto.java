package com.sparta.bizee.dto;

import com.sparta.bizee.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final long id;
    private final String content;
    private final long userId;
    private final LocalDateTime commentDate;
    private final long scheduleId;

    public CommentResponseDto(Comment comment, long scheduleId) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.commentDate = comment.getCommentDate();
        this.scheduleId = scheduleId;
    }
}
