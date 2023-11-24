package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.controller.dto.TaskDto;
import org.taskmanagement.controller.dto.TaskUpdateDto;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
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

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable int taskId, @RequestBody TaskUpdateDto taskUpdateDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            if (taskUpdateDto.getStatus() != null) {
                task.setStatus(taskUpdateDto.getStatus());
            }
            if (taskUpdateDto.getPriority() != null) {
                task.setPriority(taskUpdateDto.getPriority());
            }

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

            Project project = task.getProject();
            User user = task.getUser();
            if (project != null) {
                // update project logic TODO
            }
            if (user != null) {
                // update user logic TODO
            }

            // Finally, delete the task
            taskRepository.delete(task);
            return ResponseEntity.ok().build(); // You can also return a custom message if needed
        } else {
            return ResponseEntity.notFound().build();
        }
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


