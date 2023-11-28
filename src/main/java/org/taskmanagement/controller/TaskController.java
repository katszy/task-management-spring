package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("Calling GET /tasks/all endpoint.");
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/all2")
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("Calling GET /tasks/all2 endpoint.");
        PageRequest pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<Task> tasks = taskRepository.findAll(pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getProjectById(@PathVariable int taskId) {
        log.info("Calling GET /tasks/{taskID} endpoint.");
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            return ResponseEntity.ok(taskOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
        log.info("Calling GET /tasks/by-project/{projectId} endpoint.");
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        log.info("Calling GET /tasks/by-user/{userId} endpoint.");
        List<Task> tasks = taskRepository.findByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/new")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        log.info("Calling POST /tasks/new endpoint.");
        Task newTask = new Task();
        newTask.setTitle(taskDto.getTitle());
        newTask.setDueDate(taskDto.getDueDate());
        newTask.setStatus(taskDto.getStatus());
        newTask.setPriority(taskDto.getPriority());
        projectRepository.findById(taskDto.getProjectId()).ifPresent(newTask::setProject);
        userRepository.findById(taskDto.getUserId()).ifPresent(newTask::setUser);

        Task savedTask = taskRepository.save(newTask);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{taskId}/update/priority")
    public ResponseEntity<Task> updateTaskPriority(@PathVariable int taskId, @RequestBody PriorityUpdateDto taskUpdateDto) {
        log.info("Calling PUT /tasks/{taskId}/update/priority endpoint.");
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

        log.info("Calling PUT /tasks//{taskId}/update/status endpoint");
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

        log.info("Calling DELETE /tasks//{taskId} endpoint");
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

        log.info("Calling GET /tasks/{taskId}/comments endpoint");
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

        log.info("Calling POST /tasks/{taskId}/new-comment endpoint");
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


