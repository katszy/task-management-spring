package org.taskmanagement.repository;

import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUsername(String username);
    List<Task> viewTaskByUser(String username);
    void assignTask(String username, Task task);
}
