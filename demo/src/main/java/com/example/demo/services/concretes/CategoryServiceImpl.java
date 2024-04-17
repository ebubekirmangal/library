package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Category;
import com.example.demo.mappers.CategoryMapper;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.abstracts.CategoryService;
import com.example.demo.services.dtos.requests.category.AddCategoryRequest;
import com.example.demo.services.dtos.requests.category.DeleteCategoryRequest;
import com.example.demo.services.dtos.requests.category.UpdateCategoryRequest;
import com.example.demo.services.dtos.responses.category.AddCategoryResponse;
import com.example.demo.services.dtos.responses.category.DeleteCategoryResponse;
import com.example.demo.services.dtos.responses.category.GetAllCategoryResponse;
import com.example.demo.services.dtos.responses.category.UpdateCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public AddCategoryResponse add(AddCategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.categoryToAddCategoryRequest(request);
        Category saved = categoryRepository.save(category);

        AddCategoryResponse response = CategoryMapper.INSTANCE.addCategoryResponseToCategory(saved);

        return response;
    }

    @Override
    public UpdateCategoryResponse update(UpdateCategoryRequest request) {

        Category category = CategoryMapper.INSTANCE.categoryToUpdateCategoryRequest(request);
        Category updated = categoryRepository.save(category);

        UpdateCategoryResponse response = CategoryMapper.INSTANCE.updateCategoryResponse(updated);

        return response;
    }

    @Override
    public DeleteCategoryResponse delete(DeleteCategoryRequest request) {
        Category categoryId = categoryRepository.findById(request.getId()).orElseThrow(()-> new BusinessException("id bulunamadı."));
        categoryRepository.delete(categoryId);

        DeleteCategoryResponse response = CategoryMapper.INSTANCE.deleteCategoryResponseToCategory(categoryId);
        return response;
    }

    @Override
    public List<GetAllCategoryResponse> getAll() {

        List<Category> categories = categoryRepository.findAll();
        List<GetAllCategoryResponse> result = new ArrayList<>();

        for(Category category:categories){
            GetAllCategoryResponse dto = CategoryMapper.INSTANCE.getAllCategoryResponseToCategory(category);
            result.add(dto);
        }

        return result;
    }
}
