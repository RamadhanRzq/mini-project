package com.prodemy.miniproject.dto;

import com.prodemy.miniproject.model.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Long categoryId;
    private double productPrice;
    private String productDescription;
    private String imageName;
}
