package org.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();
}
