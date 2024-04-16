package com.example.demo.repositories;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReposiyory extends JpaRepository<Category,Integer> {
}
