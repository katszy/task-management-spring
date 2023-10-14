package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
import org.taskmanagement.domain.User;
import org.taskmanagement.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.taskmanagement.repository.mocks.MockCommentRepository.COMMENT_1;
import static org.taskmanagement.repository.mocks.MockUserRepository.USERS;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MockTaskRepository implements TaskRepository {

    public static final Task TASK_1  = new Task(1,
            "Research gyros places",
            LocalDate.of(2023, 10, 13),
            "started",
            1,
            null,
            new ArrayList<>(List.of(COMMENT_1))
    );
    public static final Task TASK_2  = new Task(2,
            "Research Greek holiday destinations",
            LocalDate.of(2023, 10, 23),
            "started",
            1,
            USERS,
            null
    );

   static List<Task> TASK = List.of(TASK_1, TASK_2);

    @Override
    public void modifyTaskAssignedUser(User user) {

    }

    @Override
    public void modifyTaskPriority(int priority) {

    }

    @Override
    public void modifyTaskStatus(String status) {

    }

    @Override
    public void createTask(Task task) {

    }

    @Override
    public void deleteTask(int taskId) {

    }

    @Override
    public List<Task> viewTaskByUser(String username) {
        return null;
    }

    @Override
    public List<Comment> viewTaskComments(int taskId) {
        Task task = TASK.get(taskId-1);
        return task.getComments();
    }

    @Override
    public void addTaskComments(int taskId, Comment comment) {
        Task task = TASK.get(taskId-1);
        task.addComment(comment);
    }

}
