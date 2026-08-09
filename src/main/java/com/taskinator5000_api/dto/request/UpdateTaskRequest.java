package com.taskinator5000_api.dto.request;

import com.taskinator5000_api.enums.TaskPriority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateTaskRequest {

    @Size(min = 1,max =  100, message = "Title must have at most 100 characters.")
    private String title;

    @Size(max = 500, message = "Description must have at most 500 characters.")
    private String description;

    private TaskPriority priority;

    @FutureOrPresent(message = "A data de vencimento deve ser hoje ou em uma data futura.")
    private LocalDate dueDate;

    @FutureOrPresent(message = "O lembrete deve ser hoje ou em uma data futura.")
    private LocalDateTime reminderAt;

    @Positive(message = "Valores negativos como id não são permitidos")
    private Long categoryId;

    public UpdateTaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReminderAt() {
        return reminderAt;
    }

    public void setReminderAt(LocalDateTime reminderAt) {
        this.reminderAt = reminderAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}