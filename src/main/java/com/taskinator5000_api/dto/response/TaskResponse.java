package com.taskinator5000_api.dto.response;


import com.taskinator5000_api.enums.TaskPriority;
import com.taskinator5000_api.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskResponse {

    @Schema(description = "Identificador único da tarefa", example = "1")
    private Long id;

    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    private String title;

    @Schema(description = "Descrição detalhada da tarefa", example = "Estudar documentação do Spring Boot e implementar os endpoints.")
    private String description;

    @Schema(description = "Status atual da tarefa", example = "PENDING")
    private TaskStatus status;

    @Schema(description = "Prioridade da tarefa", example = "HIGH")
    private TaskPriority priority;

    @Schema(description = "Data de vencimento da tarefa", example = "2026-08-25")
    private LocalDate dueDate;

    @Schema(description = "Data e hora programadas para o envio do lembrete por e-mail", example = "2026-08-25T18:00:00")
    private LocalDateTime reminderAt;

    @Schema(description = "Data e hora em que a tarefa foi concluída. Será nulo enquanto a tarefa não estiver concluída.", example = "2026-08-24T15:30:00")
    private LocalDateTime completedAt;

    @Schema(description = "Data e hora em que a tarefa foi criada", example = "2026-08-20T10:15:00")
    private LocalDateTime createdAt;

    @Schema(description = "Data e hora da última atualização da tarefa", example = "2026-08-21T14:20:00")
    private LocalDateTime updatedAt;

    @Schema(description = "Categoria associada à tarefa")
    private CategoryResponse category;

    public TaskResponse() {
    }

    public TaskResponse(Long id, String title, String description, TaskStatus status, TaskPriority priority, LocalDate dueDate,
            LocalDateTime reminderAt, LocalDateTime completedAt, LocalDateTime createdAt, LocalDateTime updatedAt, CategoryResponse category) {

        this.id = id; this.title = title; this.description = description; this.status = status; this.priority = priority;
        this.dueDate = dueDate; this.reminderAt = reminderAt; this.completedAt = completedAt; this.createdAt = createdAt;
        this.updatedAt = updatedAt; this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }
}