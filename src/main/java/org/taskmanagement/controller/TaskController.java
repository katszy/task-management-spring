package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.controller.dto.TaskDto;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
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
        List<Task> tasks = taskRepository.findByProjectId(userId);
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("/new")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
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

    /*
    @GetMapping("tasks/{taskId}/comments")
    public List<Comment> getComments(@PathVariable int taskId) {
        log.trace("Calling GET /tasks/{taskId}/comments endpoint.");
        List<Comment> comments = taskRepository.viewTaskComments(taskId);
        return comments;
    }

    @PutMapping("tasks/{taskId}/comments")
    public void addComments(@PathVariable int taskId, @RequestBody Comment comment)
    {
        log.trace("Calling PUT /tasks/{taskId}/comments endpoint.");
        taskRepository.addTaskComments(taskId,comment);
    }

    @PutMapping("tasks/{taskId}/status")
    public void modifyStatus(@PathVariable int taskId, @RequestBody Map<String, String> request) {
        log.trace("Calling PUT /tasks/{taskId}/status endpoint.");
        String newStatus = request.get("newStatus");
        taskRepository.modifyTaskStatus(taskId, newStatus);
    }

    @PutMapping("tasks/{taskId}/priority")
    public void modifyPriority(@PathVariable int taskId, @RequestBody int newPriority) {
        log.trace("Calling PUT /tasks/{taskId}/priority endpoint.");
        taskRepository.modifyTaskPriority(taskId, newPriority);
    }*/
}


