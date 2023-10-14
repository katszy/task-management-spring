package org.taskmanagement.repository;

import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;

import java.util.List;

public interface TaskRepository {

    void modifyTaskAssignedUser(User user);
    void modifyTaskPriority(int priority);
    void modifyTaskStatus(String status);
    void createTask(Task task);
    void deleteTask(int taskId);
    List<Task> viewTaskByUser(String username); //move this to user?
    List<Comment> viewTaskComments(int taskId);
    void addTaskComments(int taskId, Comment comment);


}

