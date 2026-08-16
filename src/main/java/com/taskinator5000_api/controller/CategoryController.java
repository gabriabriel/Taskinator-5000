package com.taskinator5000_api.controller;

import com.taskinator5000_api.dto.request.CreateCategoryRequest;
import com.taskinator5000_api.dto.request.UpdateCategoryRequest;
import com.taskinator5000_api.dto.response.CategoryResponse;
import com.taskinator5000_api.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Operações de gerenciamento de categorias")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Operation(summary = "Criar categoria", description = "Cria uma nova categoria.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Categoria já existente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request){
        return categoryService.createCategory(request);
    }

    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "409", description = "Categoria já existente")
    })
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @Operation(summary = "Excluir categoria", description = "Exclui uma categoria existente, desde que ela não esteja sendo utilizada por nenhuma tarefa.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "409", description = "Categoria está sendo utilizada por uma tarefa")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }


    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categorias retornadas com sucesso")
    })
    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica a partir do seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable @Parameter(description = "ID da categoria", example = "1") Long id){
        return categoryService.getCategoryById(id);
    }
}
