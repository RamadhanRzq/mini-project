package com.prodemy.miniproject.repository;

import com.prodemy.miniproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
