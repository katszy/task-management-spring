package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.controller.dto.CommentDto;
import org.taskmanagement.controller.dto.ProjectDto;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.CommentRepository;
import org.taskmanagement.repository.TaskRepository;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository commentRepository;
    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments() {
        log.info("Calling GET /comments/all endpoint.");
        List<Comment> comments = commentRepository.findAll();
        return ResponseEntity.ok(comments);
    }

}
