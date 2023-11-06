package com.miniproject.pos.posprodemy.repository;

import com.miniproject.pos.posprodemy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
