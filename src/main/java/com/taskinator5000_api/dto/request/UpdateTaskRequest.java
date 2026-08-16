package com.taskinator5000_api.dto.request;

import com.taskinator5000_api.enums.TaskPriority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateTaskRequest {

    @Schema(description = "Novo título da tarefa", example = "Estudar Spring Boot")
    @Size(min = 1,max =  100, message = "O título deve ter no máximo 100 caracteres.")
    private String title;

    @Schema(description = "Nova descrição da tarefa", example = "Estudar documentação do Spring Boot.")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
    private String description;

    @Schema(description = "Nova prioridade da tarefa", example = "HIGH")
    private TaskPriority priority;

    @Schema(description = "Nova data de vencimento da tarefa", example = "2026-08-25")
    @FutureOrPresent(message = "A data de vencimento deve ser hoje ou em uma data futura.")
    private LocalDate dueDate;

    @Schema(description = "Nova data e hora para envio do lembrete por e-mail", example = "2026-08-25T18:00:00")
    @FutureOrPresent(message = "O lembrete deve ser hoje ou em uma data futura.")
    private LocalDateTime reminderAt;

    @Schema(description = "ID da nova categoria associada à tarefa", example = "1")
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