package org.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.ProjectRepository;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;

    @PostMapping("/project/new")
    public void createProject(@RequestBody Project project)
    {
        projectRepository.createProject(project);
    }

    @GetMapping("project/{projectId}/tasks")
    public List<Task> getProjectTasks(@PathVariable int projectId){
        return projectRepository.viewTasksByProject(projectId);
    }

}

