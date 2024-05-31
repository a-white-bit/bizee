package com.sparta.bizee.Repository;

import com.sparta.bizee.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
