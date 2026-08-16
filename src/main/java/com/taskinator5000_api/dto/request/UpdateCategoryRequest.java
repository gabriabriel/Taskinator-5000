package com.taskinator5000_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateCategoryRequest {
    @Schema(description = "Novo nome da categoria", example = "Estudos")
    @Size(max = 50, message = "O nome da categoria deve ter no máximo 50 caracteres")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
