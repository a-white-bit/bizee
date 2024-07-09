package com.sparta.bizee.service;

import com.sparta.bizee.dto.request.CommentRequestDto;
import com.sparta.bizee.dto.response.CommentResponseDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.entity.Comment;
import com.sparta.bizee.entity.Schedule;
import com.sparta.bizee.exception.CommentServiceException;
import com.sparta.bizee.exception.InvalidUserException;
import com.sparta.bizee.exception.ScheduleServiceException;
import com.sparta.bizee.repository.CommentRepository;
import com.sparta.bizee.repository.ScheduleRepository;
import com.sparta.bizee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        // 일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleServiceException(ResponseCodeEnum.SCHEDULE_NOT_FOUND)
        );

        // 사용자 확인
        if (!userRepository.existsById(requestDto.getUserId())) {
            throw new InvalidUserException(ResponseCodeEnum.USER_NOT_FOUND);
        }

        // 빌더 패턴 적용
        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .userId(requestDto.getUserId())
                .schedule(schedule)
                .build();

        commentRepository.save(comment);

        return new CommentResponseDto(comment, scheduleId);
    }

    public CommentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        // 일정 확인
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ScheduleServiceException(ResponseCodeEnum.SCHEDULE_NOT_FOUND);
        }

        // 댓글 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentServiceException(ResponseCodeEnum.COMMENT_NOT_FOUND)
        );

        // 사용자 확인
        if (!userRepository.existsById(requestDto.getUserId())) {
            throw new InvalidUserException(ResponseCodeEnum.USER_NOT_FOUND);
        }
        if (!Objects.equals(comment.getUserId(), requestDto.getUserId())) {
            throw new InvalidUserException(ResponseCodeEnum.USER_NOT_MATCH);
        }

        comment.update(requestDto.getContent());
        commentRepository.save(comment);
        return new CommentResponseDto(comment, scheduleId);
    }

    public void deleteComment(Long scheduleId, Long commentId, Long userId) {
        // 일정 확인
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ScheduleServiceException(ResponseCodeEnum.SCHEDULE_NOT_FOUND);
        }

        // 댓글 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ScheduleServiceException(ResponseCodeEnum.COMMENT_NOT_FOUND)
        );

        // 사용자 확인
        if (!userRepository.existsById(userId)) {
            throw new InvalidUserException(ResponseCodeEnum.USER_NOT_FOUND);
        }
        if (!Objects.equals(comment.getUserId(), userId)) {
            throw new InvalidUserException(ResponseCodeEnum.USER_NOT_MATCH);
        }

        commentRepository.delete(comment);
    }
}