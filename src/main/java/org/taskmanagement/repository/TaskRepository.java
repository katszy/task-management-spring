package org.taskmanagement.repository;

import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;

import java.util.List;

public interface TaskRepository {

    void modifyTaskPriority(int taskId, int priority);
    void modifyTaskStatus(int taskId, String status);
    void createTask(Task task);
    void deleteTask(int taskId);
    List<Comment> viewTaskComments(int taskId);
    void addTaskComments(int taskId, Comment comment);


}

