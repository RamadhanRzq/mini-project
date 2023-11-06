package com.prodemy.miniproject.service;

import java.util.List;
import java.util.Optional;

import com.prodemy.miniproject.model.Category;

public interface CategoryService {
	
	public List<Category> getAllCategories();
    public void addCategory(Category category);
    public void removeCategoryById(Long id);
    public Optional<Category> getCategoryById(Long id);

}
