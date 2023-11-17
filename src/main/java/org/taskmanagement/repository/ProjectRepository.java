package org.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAll();
    /*List<Task> viewTasksByProject(int projectId);
    void createProject(Project project);
    void addTask(Task task, int ProjectId);
    void deleteTask(int taskId, int ProjectId);*/

}
