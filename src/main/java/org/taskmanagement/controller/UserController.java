package org.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.UserRepository;
import java.util.List;
import static org.taskmanagement.repository.mocks.MockTaskRepository.TASK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers()
    {
        log.trace("Calling GET /users endpoint.");
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable("username") String username)
    {
        log.trace("Calling GET /users/{username} endpoint.");
        return userRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/tasks")
    public List<Task> getTasksByUsername(@PathVariable("username") String username)
    {
        log.trace("Calling GET /users/{username}/tasks endpoint.");
        return userRepository.viewTaskByUser(username);
    }

    @PostMapping("/users/{username}/")
    public void assignTaskToUser(@PathVariable String username, @RequestBody Task task)
    {
        log.trace("Calling POST /users/{username} endpoint.");
        User user = userRepository.findByUsername(username);
        TASK.add(task);
        user.getTasks().add(task);
    }

}
