package com.sparta.bizee.service;

import com.sparta.bizee.Repository.CommentRepository;
import com.sparta.bizee.Repository.ScheduleRepository;
import com.sparta.bizee.dto.CommentContentResponseDto;
import com.sparta.bizee.dto.CommentRequestDto;
import com.sparta.bizee.dto.CommentResponseDto;
import com.sparta.bizee.dto.HttpStatusResponseDto;
import com.sparta.bizee.entity.Comment;
import com.sparta.bizee.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        // 일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment();
        comment.setContent(requestDto.getContent());
        comment.setUserId(requestDto.getUserId());
        comment.setSchedule(schedule);

        commentRepository.save(comment);

        return new CommentResponseDto(comment, scheduleId);
    }

    @Transactional
    public CommentContentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        // 일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        // 댓글 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        // 사용자 확인
        if (!Objects.equals(comment.getUserId(), requestDto.getUserId())) {
            throw new IllegalArgumentException("잘못된 사용자 ID입니다.");
        }

        comment.setContent(requestDto.getContent());
        return new CommentContentResponseDto(requestDto.getContent());
    }

    public HttpStatusResponseDto deleteComment(Long scheduleId, Long commentId, Long userId) {
        // 일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        // 댓글 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        // 사용자 확인
        if (!Objects.equals(comment.getUserId(), userId)) {
            throw new IllegalArgumentException("잘못된 사용자 ID입니다.");
        }

        commentRepository.delete(comment);
        return new HttpStatusResponseDto(HttpStatus.OK.toString(), "댓글이 삭제되었습니다.");
    }
}