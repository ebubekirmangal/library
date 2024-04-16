package com.example.demo.mappers;

import com.example.demo.entities.Category;
import com.example.demo.services.dtos.requests.category.AddCategoryRequest;
import com.example.demo.services.dtos.requests.category.UpdateCategoryRequest;
import com.example.demo.services.dtos.responses.category.AddCategoryResponse;
import com.example.demo.services.dtos.responses.category.DeleteCategoryResponse;
import com.example.demo.services.dtos.responses.category.GetAllCategoryResponse;
import com.example.demo.services.dtos.responses.category.UpdateCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryToAddCategoryRequest(AddCategoryRequest request);
    @Mapping(target ="bookNames",source ="book.name")
    AddCategoryResponse addCategoryResponseToCategory(Category category);

    Category categoryToUpdateCategoryRequest(UpdateCategoryRequest request);
    @Mapping(target ="bookNames",source ="book.name")
    UpdateCategoryResponse updateCategoryResponseToCategory(Category category);
    @Mapping(target ="bookNames",source ="book.name")
    DeleteCategoryResponse deleteCategoryResponseToCategory(Category category);
    @Mapping(target ="bookNames",source ="book.name")
    GetAllCategoryResponse getAllCategoryResponseToCategory(Category category);
}
