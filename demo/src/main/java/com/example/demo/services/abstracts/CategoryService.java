package com.example.demo.services.abstracts;

import com.example.demo.entities.Category;
import com.example.demo.services.dtos.requests.category.AddCategoryRequest;
import com.example.demo.services.dtos.requests.category.UpdateCategoryRequest;
import com.example.demo.services.dtos.responses.category.AddCategoryResponse;
import com.example.demo.services.dtos.responses.category.DeleteCategoryResponse;
import com.example.demo.services.dtos.responses.category.GetAllCategoryResponse;
import com.example.demo.services.dtos.responses.category.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {

    AddCategoryResponse add(AddCategoryRequest request);

    UpdateCategoryResponse update(UpdateCategoryRequest request);

    DeleteCategoryResponse delete(int id);

    List<GetAllCategoryResponse> getAll();

    Category findById(int id);
}
