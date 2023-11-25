package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.controller.dto.*;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.CommentRepository;
import org.taskmanagement.repository.ProjectRepository;
import org.taskmanagement.repository.TaskRepository;
import org.taskmanagement.repository.UserRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/new")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        Task newTask = new Task();
        newTask.setTitle(taskDto.getTitle());
        newTask.setDueDate(taskDto.getDueDate());
        newTask.setStatus(taskDto.getStatus());
        newTask.setPriority(taskDto.getPriority());

        System.out.println("OK");

        projectRepository.findById(taskDto.getProjectId()).ifPresent(newTask::setProject);
        userRepository.findById(taskDto.getUserId()).ifPresent(newTask::setUser);

        Task savedTask = taskRepository.save(newTask);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{taskId}/update/priority")
    public ResponseEntity<Task> updateTaskPriority(@PathVariable int taskId, @RequestBody PriorityUpdateDto taskUpdateDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setPriority(taskUpdateDto.getNewPriority());
            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{taskId}/update/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable int taskId, @RequestBody StatusUpdateDto taskUpdateDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setStatus(taskUpdateDto.getNewStatus());
            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{taskId}")
    @Transactional
    public ResponseEntity<?> deleteTask(@PathVariable int taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            // Deleting associated comments
            commentRepository.deleteAll(task.getComments());
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<List<Comment>> viewComments(@PathVariable int taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            List<Comment> comments = task.getComments();
            return ResponseEntity.ok(comments != null ? comments : Collections.emptyList());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{taskId}/new-comment")
    public ResponseEntity<Comment> addCommentToTask(@PathVariable int taskId, @RequestBody CommentDto commentDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Comment newComment = new Comment();
            newComment.setText(commentDto.getText());
            newComment.setTask(taskOptional.get());

            Comment savedComment = commentRepository.save(newComment);
            return ResponseEntity.ok(savedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


