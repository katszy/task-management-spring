package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.controller.dto.ProjectDto;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.ProjectRepository;
import org.taskmanagement.repository.TaskRepository;
import org.taskmanagement.repository.UserRepository;

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
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable int projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isPresent()) {
            return ResponseEntity.ok(projectOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectDto.toEntity();
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<Task>> getProjectTasks(@PathVariable int projectId) {
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

