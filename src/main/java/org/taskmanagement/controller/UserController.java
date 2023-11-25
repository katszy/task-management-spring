package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        //log.trace("Calling GET /users/{userId} endpoint.");
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable int userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Task> tasks = user.getTasks();
            return ResponseEntity.ok(tasks != null ? tasks : Collections.emptyList());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
