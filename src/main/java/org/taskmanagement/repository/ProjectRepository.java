package org.taskmanagement.repository;

import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;

import java.util.List;

public interface ProjectRepository {

    List<Task> viewTasksByProject(int projectId);
    Project viewProject(int projectId);
    void createProject(Project project);

}
