package com.prodemy.miniproject.service;

import java.util.List;
import java.util.Optional;

import com.prodemy.miniproject.model.Product;

public interface ProductService {
	public List<Product> getAllProducts();
    public void addProduct(Product product);
    public void removeProductById(Long id);
    public Optional<Product> getProductById(Long id);
    public List<Product> getAllProductsById(Long id);

}
