package com.prodemy.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodemy.miniproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
