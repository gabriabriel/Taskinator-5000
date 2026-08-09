package com.taskinator5000_api.dto.request;

import com.taskinator5000_api.enums.TaskPriority;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateTaskRequest {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ser menor que 100 caracteres.")
    private String title;

    @Size(max = 500, message = "A descrição dever ser menor que 500 caracteres.")
    private String description;

    @NotNull(message = "A prioridade deve ser definida.")
    private TaskPriority priority;

    @NotNull(message = "A data de vencimento é obrigatória.")
    @FutureOrPresent(message = "A data de vencimento deve ser hoje ou em uma data futura.")
    private LocalDate dueDate;

    @FutureOrPresent(message = "O lembrete deve ser hoje ou em uma data futura")
    private LocalDateTime reminderAt;

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
