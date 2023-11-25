package org.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAll();
    List<Task> findByProjectId(Long projectId);
    List<Task> findByUserId(Long userId);
}

