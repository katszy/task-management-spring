package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.repository.CommentRepository;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository commentRepository;


    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

}
