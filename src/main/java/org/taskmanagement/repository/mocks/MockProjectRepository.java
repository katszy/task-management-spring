package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.ProjectRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MockProjectRepository implements ProjectRepository {

    public static Project PROJECT_1 = new Project(1,"GyrosProject",null, null);
    public static  Project PROJECT_2 = new Project(2,"GreeceProject",null, null);

    @Override
    public List<Task> viewTaskByProject(int projectId) {
        return null;
    }

    @Override
    public Project viewProject(int projectId) {
        return null;
    }

    @Override
    public void createProject(Project project) {

    }

    @Override
    public void deleteProject(int projectId) {

    }
}
