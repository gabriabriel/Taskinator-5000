package com.taskinator5000_api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoryResponse {
    @Schema(description = "Identificador único da categoria", example = "1")
    private Long id;
    @Schema(description = "Nome da categoria", example = "Estudos")
    private String name;

    public CategoryResponse(){
    }

    public CategoryResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
