package org.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    /*List<Task> viewTasksByProject(int projectId);
    void createProject(Project project);
    void addTask(Task task, int ProjectId);
    void deleteTask(int taskId, int ProjectId);*/

}
