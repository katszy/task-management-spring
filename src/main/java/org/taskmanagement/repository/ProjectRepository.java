package org.taskmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanagement.domain.Project;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAll();
    Project findProjectById(Long id);
}
