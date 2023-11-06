package com.miniproject.pos.posprodemy.service;

import com.miniproject.pos.posprodemy.model.Category;
import com.miniproject.pos.posprodemy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void removeCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
}
