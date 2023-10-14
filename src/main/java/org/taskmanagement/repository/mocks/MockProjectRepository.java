package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.taskmanagement.repository.mocks.MockTaskRepository.TASK_1;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MockProjectRepository implements ProjectRepository {

    public static Project PROJECT_1 = new Project(1,"Greece travel", List.of(TASK_1));
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
    public Project viewProject(int projectId) {
        Optional<Project> projectOptional = PROJECT.stream()
                .filter(proj->proj.getId()==projectId)
                .findFirst();

        return projectOptional.orElse(null);
    }
}
