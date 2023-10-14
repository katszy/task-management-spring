package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.ProjectRepository;
import java.util.*;
import static org.taskmanagement.repository.mocks.MockTaskRepository.TASK;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MockProjectRepository implements ProjectRepository {

    public static Project PROJECT_1 = new Project(1,"Greece travel", (ArrayList<Task>) TASK);
    public static  Project PROJECT_2 = new Project(2,"Italy travel", null);
    List<Project> PROJECT = List.of(PROJECT_1,PROJECT_2);

    @Override
    public List<Task> viewTasksByProject(int projectId) {
        Optional<Project> projectOptional = PROJECT.stream()
                .filter(proj->proj.getId()==projectId)
                .findFirst();

        List<Task> projectTasks = null;
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            projectTasks = project.getTasks();
        } else {
            log.warn("No such project exists");
        }
        return projectTasks;
    }

    @Override
    public void createProject(Project project) {
        List<Project> projectList = new ArrayList<>(PROJECT);
        projectList.add(project);
        PROJECT = List.copyOf(projectList);
    }

    @Override
    public void addTask(Task task, int projectId) {
        Optional<Project> projectOptional = PROJECT.stream()
                .filter(proj->proj.getId()==projectId)
                .findFirst();
        if (projectOptional.isPresent()) {
            projectOptional.get().addTask(task);

        } else {
            log.warn("No such project exists");
        }
    }

    @Override
    public void deleteTask(int taskId, int projectId) {
        Optional<Project> projectOptional = PROJECT.stream()
                .filter(proj->proj.getId()==projectId )
                .findFirst();
        if (projectOptional.isPresent()) {
            projectOptional.get().removeTask(taskId);

        } else {
            log.warn("No such project exists");
        }
    }
}
