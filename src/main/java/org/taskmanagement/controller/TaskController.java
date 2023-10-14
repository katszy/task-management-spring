package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.*;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.TaskRepository;
import org.taskmanagement.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class TaskController {

    public static class TaskCreationRequest {

        TaskCreationRequest(){}
        private Task task;
        private List<String> usernames;
        public Task getTask() {
            return task;
        }
        public List<String> getUsernames() {
            return usernames;
        }
    }


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private List<User> getUsers(List<String> usernames) {
        List<User> users = new ArrayList<>();
        for (String username : usernames) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    @PostMapping("/tasks/new")
    public void createTask(@RequestBody TaskCreationRequest request) {
        Task task = request.getTask();
        List<String> usernames = request.getUsernames();

        List<User> users = getUsers(usernames);
        for (User user : users) {
            user.addTaskToList(task);
        }
        taskRepository.createTask(task);
    }

    @DeleteMapping("/tasks/{taskId}/delete")
    public void createTask(@PathVariable int taskId) {
       taskRepository.deleteTask(taskId);
    }

    @PutMapping("tasks/{taskId}/comments")
    public void addComments(@PathVariable int taskId, @RequestBody Comment comment)
    {
        taskRepository.addTaskComments(taskId,comment);
    }

    @GetMapping("tasks/{taskId}/comments")
    public List<Comment> getComments(@PathVariable int taskId) {
        List<Comment> comments = taskRepository.viewTaskComments(taskId);
        return comments;
    }
}


