package com.prodemy.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodemy.miniproject.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
    public List<Product> findAllByCategory_Id(Long id);

    @Query("SELECT product FROM Product product WHERE CONCAT(product.id, ' ', product.productName, ' ', product.productPrice, ' ', product.productDescription) LIKE %:keyName%")
    public List<Product> searchByName(@Param("keyName") String keyName);
}
