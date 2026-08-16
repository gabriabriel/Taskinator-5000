package com.taskinator5000_api.controller;

import com.taskinator5000_api.dto.request.CreateTaskRequest;
import com.taskinator5000_api.dto.request.UpdateTaskRequest;
import com.taskinator5000_api.dto.response.TaskResponse;
import com.taskinator5000_api.enums.TaskPriority;
import com.taskinator5000_api.enums.TaskStatus;
import com.taskinator5000_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas",  description = "Operações de gerenciamento de tarefas")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request){
        return taskService.createTask(request);
    }

    @GetMapping
    @Operation(summary = "Listar tarefas", description = "Retorna todas as tarefas cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso")
    })
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica a partir do seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable @Parameter(description = "ID da tarefa", example = "1") Long id){
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza parcialmente os dados de uma tarefa existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tarefa ou categoria não encontrada")
    })
    @PatchMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request){
        return taskService.updateTask(id, request);
    }

    @Operation(summary = "Excluir tarefa", description = "Exclui uma tarefa existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable  @Parameter(description = "ID da tarefa", example = "1") Long id){
        taskService.deleteTask(id);
    }

    @Operation(
            summary = "Concluir tarefa",
            description = "Marca uma tarefa como concluída e registra a data e hora da conclusão."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa concluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "409", description = "Tarefa já está concluída")
    })
    @PatchMapping("/{id}/concluir")
    public TaskResponse completeTask(@PathVariable @Parameter(description = "ID da tarefa", example = "1") Long id){
        return taskService.completeTask(id);
    }

    @Operation(summary = "Reabrir tarefa", description = "Reabre uma tarefa concluída, alterando seu status para PENDING.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa reaberta com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "409", description = "Tarefa ainda não está concluída")
    })
    @PatchMapping("/{id}/reabrir")
    public TaskResponse reopenTask(@PathVariable @Parameter(description = "ID da tarefa", example = "1") Long id){
        return taskService.reopenTask(id);
    }

    @Operation(summary = "Listar tarefas por status", description = "Retorna todas as tarefas que possuem o status informado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status inválido")
    })
    @GetMapping("/status/{status}")
    public List<TaskResponse> getTaskByStatus(@PathVariable @Parameter(description = "Status da tarefa", example = "PENDING") TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @Operation(summary = "Listar tarefas por prioridade", description = "Retorna todas as tarefas que possuem a prioridade informada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Prioridade inválida")
    })
    @GetMapping("/prioridade/{priority}")
    public List<TaskResponse> getTasksByPriority(@PathVariable @Parameter(description = "Prioridade da tarefa", example = "HIGH") TaskPriority priority) {
        return taskService.getTasksByPriority(priority);
    }

    @Operation(summary = "Listar tarefas por categoria", description = "Retorna todas as tarefas associadas à categoria informada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/categorias/{categoryId}")
    public List<TaskResponse> getTasksByCategory(@PathVariable @Parameter(description = "ID da categoria", example = "1") Long categoryId) {
        return taskService.getTasksByCategory(categoryId);
    }

    @Operation(summary = "Listar tarefas por vencimento", description = "Retorna as tarefas ordenadas pela data de vencimento, da mais próxima para a mais distante.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso")
    })
    @GetMapping("/vencimento")
    public List<TaskResponse> getTasksDueSoon() {
        return taskService.getTasksDueSoon();
    }

    @Operation(summary = "Listar tarefas por data de criação", description = "Retorna as tarefas ordenadas pela data de criação, da mais recente para a mais antiga.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso")
    })
    @GetMapping("/criadas")
    public List<TaskResponse> getTasksOrderByCreatedAt() {
        return taskService.getTasksOrderByCreatedAt();
    }


}
