package com.sparta.bizee.service;

import com.sparta.bizee.Repository.CommentRepository;
import com.sparta.bizee.Repository.ScheduleRepository;
import com.sparta.bizee.dto.CommentContentResponseDto;
import com.sparta.bizee.dto.CommentRequestDto;
import com.sparta.bizee.dto.CommentResponseDto;
import com.sparta.bizee.entity.Comment;
import com.sparta.bizee.entity.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(Long scheduleId, Long userId, CommentRequestDto requestDto) {
        // 일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment();
        comment.setContent(requestDto.getContent());
        comment.setUserId(userId);
        comment.setSchedule(schedule);

        commentRepository.save(comment);

        return new CommentResponseDto(comment, scheduleId);
    }

    @Transactional
    public CommentContentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        comment.setContent(requestDto.getContent());
        return new CommentContentResponseDto(requestDto.getContent());
    }
}
