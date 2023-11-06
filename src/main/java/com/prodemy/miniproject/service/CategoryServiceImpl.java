package com.prodemy.miniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodemy.miniproject.model.Category;
import com.prodemy.miniproject.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
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
