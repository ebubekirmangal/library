package com.example.demo.services.concretes;

import com.example.demo.repositories.CategoryReposiyory;
import com.example.demo.services.abstracts.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryReposiyory categoryReposiyory;

    public CategoryServiceImpl(CategoryReposiyory categoryReposiyory) {
        this.categoryReposiyory = categoryReposiyory;
    }
}
