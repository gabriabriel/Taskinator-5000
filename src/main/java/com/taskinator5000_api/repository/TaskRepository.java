package com.taskinator5000_api.repository;

import com.taskinator5000_api.entity.Task;
import com.taskinator5000_api.entity.Category;
import com.taskinator5000_api.enums.TaskPriority;
import com.taskinator5000_api.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByCategory(Category category);

    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findByDueDateBefore(LocalDate date);

    List<Task> findByDueDateLessThanEqual(LocalDate date);

    List<Task> findAllByOrderByDueDateAsc();

    List<Task> findAllByOrderByCreatedAtDesc();

    boolean existsByCategory(Category category);

    @Query("""
       SELECT t
       FROM Task t
       JOIN FETCH t.category
       WHERE t.id = :id
       """)
    Optional<Task> findByIdWithCategory(Long id);

    @Query("""
       SELECT t
       FROM Task t
       JOIN FETCH t.category
       WHERE t.reminderAt <= :now
       AND t.reminderSent = false
       AND t.status <> :completedStatus
       """)
    List<Task> findTasksWithPendingReminders(
            LocalDateTime now,
            TaskStatus completedStatus
    );
}
