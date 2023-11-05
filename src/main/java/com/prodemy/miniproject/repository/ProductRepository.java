package com.prodemy.miniproject.repository;

import com.prodemy.miniproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Id(Long id);
}
