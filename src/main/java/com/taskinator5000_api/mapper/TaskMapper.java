package com.taskinator5000_api.mapper;

import com.taskinator5000_api.dto.request.CreateTaskRequest;
import com.taskinator5000_api.dto.request.UpdateTaskRequest;
import com.taskinator5000_api.dto.response.CategoryResponse;
import com.taskinator5000_api.dto.response.TaskResponse;
import com.taskinator5000_api.entity.Category;
import com.taskinator5000_api.entity.Task;
import com.taskinator5000_api.enums.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final CategoryMapper categoryMapper;

    public TaskMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Task toEntity(CreateTaskRequest request, Category category){
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(TaskStatus.PENDING);
        task.setDueDate(request.getDueDate());
        task.setReminderAt(request.getReminderAt());
        task.setCategory(category);

        return task;
    }

    public TaskResponse toResponse(Task task){

        CategoryResponse categoryResponse = categoryMapper.toResponse(task.getCategory());

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getReminderAt(),
                task.getCompletedAt(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                categoryResponse
        );
    }

    public void updateEntity(Task task, UpdateTaskRequest request, Category category){

        if(request.getTitle() != null){
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }

        if (request.getReminderAt() != null) {
            task.setReminderAt(request.getReminderAt());
            task.setReminderSent(false);
        }

        if (category != null) {
            task.setCategory(category);
        }
    }
}