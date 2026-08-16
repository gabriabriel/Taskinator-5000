package com.taskinator5000_api.dto.request;

import com.taskinator5000_api.enums.TaskPriority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateTaskRequest {

    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
    private String title;

    @Schema(description = "Descrição detalhada da tarefa", example = "Estudar documentação do Spring Boot e implementar os testes.")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
    private String description;

    @Schema(description = "Prioridade da tarefa", example = "HIGH")
    @NotNull(message = "A prioridade deve ser definida.")
    private TaskPriority priority;

    @Schema(description = "Data de vencimento da tarefa", example = "2026-08-25")
    @NotNull(message = "A data de vencimento é obrigatória.")
    @FutureOrPresent(message = "A data de vencimento deve ser hoje ou em uma data futura.")
    private LocalDate dueDate;

    @Schema(description = "Data e hora em que o lembrete deve ser enviado por e-mail", example = "2026-08-25T18:00:00")
    @FutureOrPresent(message = "O lembrete deve ser hoje ou em uma data futura")
    private LocalDateTime reminderAt;

    @Schema(description = "ID da categoria associada à tarefa", example = "1")
    @NotNull(message = "A categoria deve ser definida.")
    @Positive(message = "Valores negativos como id não são permitidos")
    private Long categoryId;

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
