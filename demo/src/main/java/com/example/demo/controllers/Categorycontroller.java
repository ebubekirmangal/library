package com.example.demo.controllers;

import com.example.demo.services.abstracts.CategoryService;
import com.example.demo.services.dtos.requests.category.AddCategoryRequest;
import com.example.demo.services.dtos.requests.category.UpdateCategoryRequest;
import com.example.demo.services.dtos.responses.category.AddCategoryResponse;
import com.example.demo.services.dtos.responses.category.DeleteCategoryResponse;
import com.example.demo.services.dtos.responses.category.GetAllCategoryResponse;
import com.example.demo.services.dtos.responses.category.UpdateCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class Categorycontroller {

    private CategoryService categoryService;

    public Categorycontroller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    private AddCategoryResponse add(@RequestBody AddCategoryRequest request){

        return categoryService.add(request);
    }

    @PutMapping("/update")
    private UpdateCategoryResponse update(@RequestBody UpdateCategoryRequest request){

        return categoryService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    private DeleteCategoryResponse delete(@PathVariable("id") int id){
        return categoryService.delete(id);
    }

    @GetMapping("/getAll")
    private List<GetAllCategoryResponse> getAll(){
        return categoryService.getAll();
    }
}
