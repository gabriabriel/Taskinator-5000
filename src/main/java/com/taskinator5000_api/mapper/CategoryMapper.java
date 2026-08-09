package com.taskinator5000_api.mapper;

import com.taskinator5000_api.dto.request.CreateCategoryRequest;
import com.taskinator5000_api.dto.request.UpdateCategoryRequest;
import com.taskinator5000_api.dto.response.CategoryResponse;
import com.taskinator5000_api.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequest request) {

        Category category = new Category();
        category.setName(request.getName());

        return category;
    }

    public CategoryResponse toResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    public void updateEntity(Category category, UpdateCategoryRequest request){
        if(request.getName() != null){
            category.setName(request.getName());
        }
    }

}
