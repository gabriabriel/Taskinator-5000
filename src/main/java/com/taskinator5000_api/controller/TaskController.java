package com.taskinator5000_api.controller;

import com.taskinator5000_api.dto.request.CreateTaskRequest;
import com.taskinator5000_api.dto.request.UpdateTaskRequest;
import com.taskinator5000_api.dto.response.TaskResponse;
import com.taskinator5000_api.enums.TaskPriority;
import com.taskinator5000_api.enums.TaskStatus;
import com.taskinator5000_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request){
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PatchMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request){
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/concluir")
    public TaskResponse completeTask(@PathVariable Long id){
        return taskService.completeTask(id);
    }

    @PatchMapping("/{id}/reabrir")
    public TaskResponse reopenTask(@PathVariable Long id){
        return taskService.reopenTask(id);
    }

    @GetMapping("/status/{status}")
    public List<TaskResponse> getTaskByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/prioridade/{priority}")
    public List<TaskResponse> getTasksByPriority(@PathVariable TaskPriority priority) {
        return taskService.getTasksByPriority(priority);
    }

    @GetMapping("/categorias/{categoryId}")
    public List<TaskResponse> getTasksByCategory(@PathVariable Long categoryId) {
        return taskService.getTasksByCategory(categoryId);
    }

    @GetMapping("/vencimento")
    public List<TaskResponse> getTasksDueSoon() {
        return taskService.getTasksDueSoon();
    }

    @GetMapping("/criadas")
    public List<TaskResponse> getTasksOrderByCreatedAt() {
        return taskService.getTasksOrderByCreatedAt();
    }


}
