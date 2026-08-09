package com.taskinator5000_api.service;

import com.taskinator5000_api.dto.request.CreateTaskRequest;
import com.taskinator5000_api.dto.request.UpdateTaskRequest;
import com.taskinator5000_api.dto.response.TaskResponse;
import com.taskinator5000_api.entity.Category;
import com.taskinator5000_api.entity.Task;
import com.taskinator5000_api.enums.TaskPriority;
import com.taskinator5000_api.enums.TaskStatus;
import com.taskinator5000_api.exception.*;
import com.taskinator5000_api.mapper.TaskMapper;
import com.taskinator5000_api.repository.CategoryRepository;
import com.taskinator5000_api.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository,
                       TaskMapper taskMapper) {

        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.taskMapper = taskMapper;
    }

    private Task findTaskById(Long id){
        return taskRepository.findByIdWithCategory(id).orElseThrow(() -> new TaskNotFoundExeption("Task com id " + id + " não enontrada."));
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Categoria com id " + id + " não encontrada."));
    }

    private void validateReminder(LocalDate dueDate, LocalDateTime reminderAt) {
        if (dueDate != null && reminderAt != null && reminderAt.toLocalDate().isAfter(dueDate)) {
            throw new TaskBusinessException("Lembrete não pode ser após a data de vencimento.");
        }
    }

    @Transactional
    public TaskResponse createTask(CreateTaskRequest request){

        Category category = findCategoryById(request.getCategoryId());
        validateReminder(request.getDueDate(), request.getReminderAt());

        Task task = taskMapper.toEntity(request,category);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks(){
        return taskRepository.findAll().stream().map(taskMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id){
        return taskMapper.toResponse(findTaskById(id));
    }

    @Transactional
    public TaskResponse updateTask(Long id, UpdateTaskRequest request){
        Task task = findTaskById(id);
        Category category = null;
        if(request.getCategoryId() != null){
            category = findCategoryById(request.getCategoryId());
        }

        LocalDate dueDate = request.getDueDate() != null ? request.getDueDate() : task.getDueDate();
        LocalDateTime reminderAt = request.getReminderAt() != null ? request.getReminderAt() : task.getReminderAt();

        validateReminder(dueDate, reminderAt);

        taskMapper.updateEntity(task, request, category);

        return taskMapper.toResponse(task);

    }

    @Transactional
    public TaskResponse completeTask(Long id){

        Task task = findTaskById(id);

        if (task.getStatus() == TaskStatus.COMPLETED){
            throw new TaskBusinessException("A tarefa já foi concluída.");
        }

        task.setStatus(TaskStatus.COMPLETED);
        task.setCompletedAt(LocalDateTime.now());

        return taskMapper.toResponse(task);
    }

    @Transactional
    public TaskResponse reopenTask(Long id) {

        Task task = findTaskById(id);

        if (task.getStatus() != TaskStatus.COMPLETED) {
            throw new TaskBusinessException("A tarefa ainda não foi concluída.");
        }

        task.setStatus(TaskStatus.PENDING);
        task.setCompletedAt(null);

        return taskMapper.toResponse(task);
    }

    @Transactional
    public void deleteTask(Long id){
        Task task = findTaskById(id);

        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream().map(taskMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);

        return taskRepository.findByCategory(category).stream().map(taskMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority).stream().map(taskMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksDueSoon() {
        return taskRepository.findAllByOrderByDueDateAsc().stream().map(taskMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksOrderByCreatedAt() {
        return taskRepository.findAllByOrderByCreatedAtDesc().stream().map(taskMapper::toResponse).toList();
    }
}
