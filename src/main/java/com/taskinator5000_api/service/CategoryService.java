package com.taskinator5000_api.service;

import com.taskinator5000_api.dto.request.CreateCategoryRequest;
import com.taskinator5000_api.dto.request.UpdateCategoryRequest;
import com.taskinator5000_api.dto.response.CategoryResponse;
import com.taskinator5000_api.entity.Category;
import com.taskinator5000_api.exception.CategoryAlreadyExistsException;
import com.taskinator5000_api.exception.CategoryInUseException;
import com.taskinator5000_api.exception.CategoryNotFoundException;
import com.taskinator5000_api.mapper.CategoryMapper;
import com.taskinator5000_api.repository.CategoryRepository;
import com.taskinator5000_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TaskRepository taskRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, TaskRepository taskRepository){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.taskRepository = taskRepository;
    }

    private Category findCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Categoria com o id " + id + "não existe"));

    }


    public CategoryResponse createCategory(CreateCategoryRequest request){

        if(categoryRepository.existsByName(request.getName())){
            throw new CategoryAlreadyExistsException("Category '" + request.getName() + "' already exists.");
        }
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();

    }

    public CategoryResponse getCategoryById(Long id){
        return categoryMapper.toResponse(findCategoryById(id));
    }

    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest request){
        Category category = findCategoryById(id);

        if(request.getName() != null && !request.getName().equals(category.getName()) && categoryRepository.existsByName(request.getName())){
            throw new CategoryAlreadyExistsException("Categoria " + request.getName() + " já existe");
        }

        categoryMapper.updateEntity(category, request);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(updatedCategory);
    }

    public void deleteCategory(Long id){
        Category category = findCategoryById(id);
        if(taskRepository.existsByCategory(category)){
            throw new CategoryInUseException("Categoria " + category.getName() + " não pode ser deletada pois está atribuída a uma ou mais tarefas");
        }
        categoryRepository.delete(category);
    }



}
