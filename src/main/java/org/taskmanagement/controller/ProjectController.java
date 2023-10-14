package org.taskmanagement.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.taskmanagement.domain.Project;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.ProjectRepository;
import org.taskmanagement.repository.TaskRepository;
import org.taskmanagement.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @PostMapping("/project/new")
    public void createProject(@RequestBody Project project)
    {
        log.trace("Calling POST /project/new endpoint.");
        projectRepository.createProject(project);
    }

    @GetMapping("/project/{projectId}/tasks")
    public List<Task> getProjectTasks(@PathVariable int projectId){
        log.trace("Calling GET /project/{projectId}/tasks endpoint.");
        return projectRepository.viewTasksByProject(projectId);
    }

    @PostMapping("/project/{projectId}/new-task")
    public void addNewTaskToProject(@RequestBody TaskCreationRequest request, @PathVariable int projectId) {
        log.trace("Calling POST /project/{projectId}/new-task endpoint.");
        Task task = request.getTask();
        List<String> usernames = request.getUsernames();

        List<User> users = getUsers(usernames);
        for (User user : users) {
            user.addTaskToList(task);
        }
        taskRepository.createTask(task);
        projectRepository.addTask(task,projectId);
    }

    @DeleteMapping("/project/{projectId}/tasks/{taskId}/delete")
    public void deleteTask(@PathVariable int projectId, @PathVariable int taskId) {
        log.trace("Calling DELETE //project/{projectId}/tasks/{taskId}/delete");
        projectRepository.deleteTask(taskId, projectId);
        taskRepository.deleteTask(taskId);
    }

    private List<User> getUsers(List<String> usernames) {
        List<User> users = new ArrayList<>();
        for (String username : usernames) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }


    public static class TaskCreationRequest {
        TaskCreationRequest(){}
        private Task task;
        private List<String> usernames;
        public Task getTask() {
            return task;
        }
        public List<String> getUsernames() {
            return usernames;
        }
    }

}

