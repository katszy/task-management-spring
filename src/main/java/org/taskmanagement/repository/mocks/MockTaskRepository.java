package org.taskmanagement.repository.mocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.taskmanagement.domain.Comment;
import org.taskmanagement.domain.Task;
import org.taskmanagement.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.taskmanagement.repository.mocks.MockCommentRepository.COMMENT_1;


@Repository
@Slf4j
@RequiredArgsConstructor
public class MockTaskRepository implements TaskRepository {

    public static final Task TASK_1  = new Task(1,
            "Research gyros places",
            LocalDate.of(2023, 10, 13),
            "started",
            1,
            new ArrayList<>(List.of(COMMENT_1))
    );
    public static final Task TASK_2  = new Task(2,
            "Research Greek holiday destinations",
            LocalDate.of(2023, 10, 23),
            "started",
            1,
            null
    );

    static List<Task> TASK = new ArrayList<>(List.of(TASK_1, TASK_2));


    @Override
    public void modifyTaskPriority(int taskId, int priority) {
        Optional<Task> taskToUpdate = TASK.stream()
                .filter(task -> task.getId() == taskId)
                .findFirst();

        if (taskToUpdate.isPresent()) {
            taskToUpdate.get().setPriority(priority);
        } else {
            throw new NoSuchElementException("Task not found");
        }
    }

    @Override
    public void modifyTaskStatus(int taskId, String status) {
        Optional<Task> taskToUpdate = TASK.stream()
                .filter(task -> task.getId() == taskId)
                .findFirst();

        if (taskToUpdate.isPresent()) {
            taskToUpdate.get().setStatus(status);
        } else {
            throw new NoSuchElementException("Task not found");
        }
    }

    @Override
    public void createTask(Task task) {
        TASK.add(task);
    }

    @Override
    public void deleteTask(int taskId) {
        TASK.removeIf(task -> task.getId() == taskId);
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
