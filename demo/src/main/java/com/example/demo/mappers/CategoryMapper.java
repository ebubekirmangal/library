package com.example.demo.mappers;

import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
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

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryToAddCategoryRequest(AddCategoryRequest request);

    AddCategoryResponse addCategoryResponseToCategory(Category category);
    Category categoryToUpdateCategoryRequest(UpdateCategoryRequest request);
    @Mapping(target = "bookNames",source = "books")
    UpdateCategoryResponse updateCategoryResponse(Category category);
    @Mapping(target = "bookNames",source = "books")
    DeleteCategoryResponse deleteCategoryResponseToCategory(Category category);
    @Mapping(target = "bookNames",source = "books")
    GetAllCategoryResponse getAllCategoryResponseToCategory(Category category);

    default List<String> mapBorrowsToBookName(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream()
                .map(Book::getName) // Corrected method reference
                .collect(Collectors.toList());
    }
}
