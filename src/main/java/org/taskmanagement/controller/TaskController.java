package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.repository.TaskRepository;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;
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


