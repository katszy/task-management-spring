package org.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanagement.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Additional custom methods can be declared here
}
