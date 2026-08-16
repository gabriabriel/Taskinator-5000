package com.taskinator5000_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCategoryRequest {
    @Schema(description = "Nome da categoria", example = "Estudos", maxLength = 50)
    @NotBlank(message = "A categoria precisa ter um nome")
    @Size(max = 50, message = "O nome da categoria deve ter no maxímo 50 caracteres")
    private String name;

    public CreateCategoryRequest(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
