package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.controller.dto.ProjectDto;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.ProjectRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectRepository projectRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        log.info("Calling GET /project/all endpoint.");
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable int projectId) {
        log.info("Calling GET /project/{projectId}} endpoint.");
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return ResponseEntity.ok(projectOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto) {
        log.info("Calling POST /project/new endpoint.");
        Project project = projectDto.toEntity();
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<Task>> getProjectTasks(@PathVariable int projectId) {
        log.info("Calling GET /project/{projectId}/tasks endpoint.");
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            List<Task> tasks = project.getTasks();
            return ResponseEntity.ok(tasks != null ? tasks : Collections.emptyList());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

